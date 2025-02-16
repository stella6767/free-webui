package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.AiMessageReqDto
import freeapp.life.freeaiweb.dto.ChatReqDto
import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.service.AiService
import freeapp.life.freeaiweb.service.ChatService
import freeapp.life.freeaiweb.view.*
import freeapp.life.freeaiweb.view.component.chatFormView
import freeapp.life.freeaiweb.view.component.chatsNavView
import mu.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.net.URI


@RestController
class IndexController(
    private val aiService: AiService,
    private val chatService: ChatService,
) {

    private val log = KotlinLogging.logger {  }

    @GetMapping("/")
    fun index(): String {
        return renderPageWithLayout {
            chatInitialView()
        }
    }

    @PostMapping("/new/chat")
    fun newChat(chatReqDto: AiMessageReqDto): String {

        val msgPair =
            aiService.createMessagePair(chatReqDto)

        val chatDto =
            ChatRespDto.fromEntity(msgPair.chat, true)

        val renderComponent = renderComponent {
            mainContentView(chatDto)
        }

        return renderComponent
    }

    @PostMapping("/chat")
    fun chat(chatReqDto: AiMessageReqDto): String {

        val msgPair =
            aiService.createMessagePair(chatReqDto)

        val userChatView = renderComponent {
            msgPairBlockView(
                msgPair,
                msgPair.chat.id.toString()
            )
        }
        return userChatView
    }



    @GetMapping("/chat/{id}")
    fun chat(
        @PathVariable id: Long,
    ): String {

        val chat =
            chatService.findChatById(id)

        return renderPageWithLayout {
            chatView(ChatRespDto.fromEntity(chat, true))
        }
    }




    @GetMapping("/chats")
    fun chats(
        @PageableDefault(size = 16) pageable: Pageable,
    ): String {

        return renderComponent {
            chatsNavView(chatService.findChatsByPage(pageable))
        }
    }



    @GetMapping(path = ["/chat-sse/{clientId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamChat(
        @PathVariable clientId: String,
    ): SseEmitter {

        return aiService.connectSse(clientId)
    }


}
