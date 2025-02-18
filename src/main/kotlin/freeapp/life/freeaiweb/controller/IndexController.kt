package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.AiMessageReqDto
import freeapp.life.freeaiweb.dto.ChatReqDto
import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.service.AiService
import freeapp.life.freeaiweb.service.ChatService
import freeapp.life.freeaiweb.view.*
import freeapp.life.freeaiweb.view.component.chatsNavView
import freeapp.life.freeaiweb.view.component.headerView
import jakarta.servlet.http.HttpServletRequest
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.header
import kotlinx.html.id
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter


@RestController
class IndexController(
    private val aiService: AiService,
    private val chatService: ChatService,
) {

    private val log = KotlinLogging.logger { }

    @GetMapping("/")
    fun index(
        request: HttpServletRequest,
    ): String {

        val html = if (request?.getHeader("HX-Request") == null) {
            renderPageWithLayout { chatInitialView() }
        } else renderComponent { div { newChatView() } }

        return html
    }

    @PostMapping("/chat")
    fun newChat(chatReqDto: AiMessageReqDto): ResponseEntity<String> {

        val msgPair =
            aiService.createMessagePair(chatReqDto)

        val chatDto =
            ChatRespDto.fromEntity(msgPair.chat, true)

        val renderComponent = renderComponent {
            div {
                mainContentView(chatDto)
                chatIdHiddenView(chatDto.id)
            }
        }

        val headers = HttpHeaders()
        headers.add("HX-Push", "/chat/${chatDto.id}")
        //headers.add("HX-Trigger", "chatsEvent")
        headers.add("HX-Trigger-After-Swap", "newChatEvent")

        return ResponseEntity(renderComponent, headers, HttpStatus.OK)
    }

    @PutMapping("/chat/{id}")
    fun updateChat(
        @PathVariable id: String,
        chatReqDto: ChatReqDto
    ) {

        println(chatReqDto)

        chatService.updateChat(chatReqDto)
    }


//    @DeleteMapping("/chat")
//    fun deleteChat(){
//
//    }


    @PostMapping("/message")
    fun message(chatReqDto: AiMessageReqDto): String {

        val msgPair =
            aiService.createMessagePair(chatReqDto)

        val userChatView = renderComponent {
            div {
                msgPairBlockView(
                    msgPair,
                )
            }
        }
        return userChatView
    }


    @GetMapping("/chat/{id}")
    fun chat(
        @PathVariable id: Long,
        request: HttpServletRequest,
    ): String {

        val chat =
            chatService.findChatRespById(id)

        val html = if (request?.getHeader("HX-Request") == null) {
            renderPageWithLayout {
                chatView(chat)
            }
        } else renderComponent {
            div { mainContentView(chat) }
        }

        return html
    }




    @GetMapping("/chats")
    fun chats(
        @PageableDefault(size = 16) pageable: Pageable,
        @RequestParam chatId:Long = 0,
    ): String {

        // 이게 문제...
        val chat = if (chatId != 0L) {
            ChatRespDto.fromEntity(chatService.findChatById(chatId), false)
        } else null

        return renderComponent {
            div {
                chatsNavView(
                    chatService.findChatsByPage(pageable ),
                    chat
                )
            }
        }
    }


    @GetMapping(path = ["/chat-sse/{clientId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamChat(
        @PathVariable clientId: String,
    ): SseEmitter {
        return aiService.connectSse(clientId)
    }


}
