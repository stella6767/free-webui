package freeapp.life.freeaiweb.service

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import freeapp.life.freeaiweb.dto.AiMessageReqDto
import freeapp.life.freeaiweb.dto.ChatReqDto
import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.Message
import freeapp.life.freeaiweb.entity.MessagePair
import mu.KotlinLogging
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestClient
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap


@Service
class AiService(
    private val chatClient:ChatClient,
    private val chatService: ChatService,
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

        // 사용자별 emiiter 가져오기 없으면 만들어내기
        val emitter = emitters.computeIfAbsent(messageReqDto.clientId) {
            println("why")
            SseEmitter(sseConnectionTime)
        }

        println("?????")

        val aiResponse = chatClient.prompt(messageReqDto.msg)
            .stream()
            .content()

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
                val html =
                    """<div id="ai-response-div-${uniqueId}" class="my-1 p-1" hx-swap-oob="outerHTML:#ai-response-div-${uniqueId}">${err.message}</div>"""
                emitter.send(
                    SseEmitter.event()
                        .name("ai-response")
                        .id(uniqueId.toString())
                        .data(html)
                )
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


}
