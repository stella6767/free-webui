package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.AiChatReqDto
import freeapp.life.freeaiweb.service.AiService
import freeapp.life.freeaiweb.view.chatMsgView
import freeapp.life.freeaiweb.view.indexView
import freeapp.life.freeaiweb.view.renderComponent
import freeapp.life.freeaiweb.view.renderPageWithLayout
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.atomic.AtomicLong


@RestController
class IndexController(
    private val aiService: AiService,
) {

    private val log = KotlinLogging.logger {  }

    private val aiResponseDivId = AtomicLong()

    @GetMapping("/test")
    fun test(): String {
        return renderPageWithLayout {
            indexView()
        }
    }


    @PostMapping("/chat")
    fun chat(chatReqDto: AiChatReqDto): String {

        val uniqueId =
            aiResponseDivId.incrementAndGet()

        aiService.sendAiResponse(chatReqDto, uniqueId)

        val userChatView = renderComponent {
            chatMsgView(
                chatReqDto.msg,
                uniqueId
            )
        }

        return userChatView
    }

    @GetMapping(path = ["/chat-sse/{clientId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamChat(
        @PathVariable clientId: String,
    ): SseEmitter {

        return aiService.connectSse(clientId)
    }


}
