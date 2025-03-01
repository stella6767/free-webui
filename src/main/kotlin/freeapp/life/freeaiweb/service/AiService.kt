package freeapp.life.freeaiweb.service

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import freeapp.life.freeaiweb.dto.AiMessageReqDto
import freeapp.life.freeaiweb.dto.ChatClientHolder
import freeapp.life.freeaiweb.dto.UploadResponseDto
import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.MessagePair
import mu.KotlinLogging
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor
import org.springframework.ai.document.Document
import org.springframework.ai.reader.tika.TikaDocumentReader
import org.springframework.ai.transformer.splitter.TokenTextSplitter
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.core.io.InputStreamResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import reactor.core.publisher.Mono
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.concurrent.ConcurrentHashMap


@Service
class AiService(
    private val chatClientHolder: ChatClientHolder,
    private val chatService: ChatService,
    private val vectorStore: VectorStore
) {

    private val log = KotlinLogging.logger { }

    private val emitters = ConcurrentHashMap<String, SseEmitter>()
    private val parser: Parser = Parser.builder().build()
    private val renderer: HtmlRenderer = HtmlRenderer.builder().build()
    private val sseConnectionTime = Long.MAX_VALUE

    @Transactional
    fun createMessagePair(messageReqDto: AiMessageReqDto): MessagePair {

        val chat: Chat =
            if (messageReqDto.chatId != 0L) {
                chatService.findChatById(messageReqDto.chatId)
            } else chatService.saveChat(messageReqDto.toChatReqDto())

        val content = messageReqDto.msg

        val humanMessage =
            chatService.saveMessage(messageReqDto.toEntity(content))

        val messagePair = chatService.saveMessagePair(
            MessagePair(
                humanMessage = humanMessage,
                aiMessage = null,
                chat = chat
            )
        )

        sendAiResponse(messagePair.id, messageReqDto)

        return chat.addMessagePair(messagePair)
    }


    private fun sendAiResponse(
        uniqueId: Long,
        messageReqDto: AiMessageReqDto,
    ) {

        //todo refactoring
        val chatClient =
            chatClientHolder.getChatClient()

        // 사용자별 emiiter 가져오기 없으면 만들어내기
        val emitter =
            emitters.computeIfAbsent(messageReqDto.clientId) {
                SseEmitter(sseConnectionTime)
            }

        val clientReq = chatClient
            .prompt(messageReqDto.msg)

        if (messageReqDto.isRag) {
            clientReq.advisors(QuestionAnswerAdvisor(vectorStore, SearchRequest.builder().build()))
        }

        val aiResponse =
            clientReq.stream().content()


        var accumulated = ""

        aiResponse
            //.delayElements(Duration.ofMillis(500))
            .doOnNext { chunk ->
                accumulated += chunk
                val htmlString =
                    markDownToHtml(accumulated)
                val html =
                    """<div id="ai-response-div-${uniqueId}" class="my-1 p-1" hx-swap-oob="outerHTML:#ai-response-div-${uniqueId}">$htmlString</div>"""
                emitter.send(
                    SseEmitter.event()
                        .name("ai-response")
                        .id(uniqueId.toString())
                        .data(html)
                )
            }
            .doOnError { err ->
                log.error { "Error occurred: ${err.message}" }
            }
            .doOnComplete {
                //"""<div id="sse-listener" hx-swap-oob="true"></div>"""
                val html = markDownToHtml(accumulated)
                val aiMessage =
                    chatService.saveMessage(messageReqDto.toEntity(html))
                println("확인!!! ${aiMessage.id}")
                chatService.updateMessagePair(uniqueId, aiMessage)
                val finalHtml =
                    """<div id="ai-response-div-${uniqueId}" class="my-1 p-1" hx-swap-oob="outerHTML:#ai-response-div-${uniqueId}">$html</div>"""
                emitter.send(
                    SseEmitter.event()
                        .name("ai-response")
                        .id(uniqueId.toString())
                        .data(finalHtml)
                )
            }
            .onErrorResume { err ->

                log.error { "ai response err::${err.message}" }
                val finalHtml =
                    """<div id="ai-response-div-${uniqueId}" class="my-1 p-1" hx-swap-oob="outerHTML:#ai-response-div-${uniqueId}">${err.message}</div>"""
                emitter.send(
                    SseEmitter.event()
                        .name("ai-response")
                        .id(uniqueId.toString())
                        .data(finalHtml)
                )
                Mono.empty()
            }
            .subscribe()

    }

    private fun markDownToHtml(chunk: String): String {
        val parseString =
            parser.parse(chunk)

        val htmlString =
            renderer.render(parseString)
        return htmlString
    }


    fun connectSse(
        clientId: String
    ): SseEmitter {

        log.info { clientId }

        val emitter = SseEmitter(sseConnectionTime)

        emitters[clientId] = emitter
        // 클라이언트가 연결을 종료하면 제거
        emitter.onCompletion {
            log.info { "종료" }
            emitters.remove(clientId)
        }
        emitter.onTimeout { emitters.remove(clientId) }
        emitter.onError { emitters.remove(clientId) }

        return emitter
    }


    fun addFileToVectorStore(file: MultipartFile): UploadResponseDto {
        println(file.originalFilename)
        // 1. InputStreamResource로 변환
        val resource = InputStreamResource(file.inputStream)
        // 2. TikaDocumentReader 생성
        val reader = TikaDocumentReader(resource)
        //Read and split the document contents
        val documents: List<Document> = reader.get()
        val splitDocuments: List<Document> = TokenTextSplitter().apply(documents)
        vectorStore.add(splitDocuments)

        return UploadResponseDto(file.originalFilename ?: "", file.contentType ?: "", file.size)
    }
    

}
