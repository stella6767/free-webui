package freeapp.life.freeaiweb.service

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import freeapp.life.freeaiweb.dto.AiChatReqDto
import mu.KotlinLogging
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap


@Service
class AiService(
    private val builder: ChatClient.Builder,
) {

    private val log = KotlinLogging.logger {  }

    private val chatClient = builder.build()
    private val emitters = ConcurrentHashMap<String, SseEmitter>()
    private val parser: Parser = Parser.builder().build()
    private val renderer: HtmlRenderer = HtmlRenderer.builder().build()


    fun sendAiResponse(chatReqDto: AiChatReqDto, uniqueId: Long) {

        // 사용자별 emiiter 가져오기 없으면 만들어내기
        val emitter = emitters.computeIfAbsent(chatReqDto.clientId) {
            SseEmitter(Long.MAX_VALUE)
        }

        val aiResponse = chatClient.prompt(chatReqDto.msg)
            .stream()
            .content()

        var streamText = ""

        aiResponse
            //.delayElements(Duration.ofMillis(500))
            .doOnNext {chunk ->
                streamText += chunk

//                if (streamText.contains(".")) {
//                    val segments =
//                        streamText.split(".")
//                    val completeSegments =
//                        segments.dropLast(1)
//
//                    completeSegments.forEach {text->
//                        val htmlString =
//                            markDownToHtml(text)
//
//                        log.info { """!!!!
//                            $htmlString
//                        """.trimMargin() }
//
//                        emitter.send(SseEmitter.event()
//                            .name("ai-response")
//                            .id(uniqueId.toString())
//                            .data(htmlString))
//                    }
//                    streamText = segments.last()
//                }

                val htmlString =
                    markDownToHtml(streamText)

                val text = """<div id="ai-response-div" hx-swap-oob="outerHTML:#ai-response-div">$htmlString</div>"""

                println(text)

                emitter.send(SseEmitter.event()
                    .name("ai-response")
                    .id(uniqueId.toString())
                    .data(text))

            }
            .doOnComplete {

                //"""<div id="sse-listener" hx-swap-oob="true"></div>"""

//                val finalHtml = markDownToHtml(streamText)
//                emitter.send(SseEmitter.event()
//                    .name("ai-response")
//                    .id(uniqueId.toString())
//                    .data(finalHtml))

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
        clientId:String
    ): SseEmitter {

        log.info { clientId }

        val emitter = SseEmitter(60 * 1000L)

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
