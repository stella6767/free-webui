package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.AiMessageReqDto
import freeapp.life.freeaiweb.dto.ChatReqDto
import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.service.AiService
import freeapp.life.freeaiweb.service.ChatService
import freeapp.life.freeaiweb.view.*
import freeapp.life.freeaiweb.view.component.chatFormView
import freeapp.life.freeaiweb.view.component.chatsNavView
import jakarta.servlet.http.HttpServletRequest
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.id
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.net.URI


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

                div {
                    id = "chat-id-box"
                    attributes["hx-get"] = "/chats"
                    attributes["hx-trigger"] = "load, chatsEvent from:body"
                    attributes["hx-vals"] = """js:{"chatId": window.location.pathname.split("/").pop()}"""
                    attributes["hx-swap-oob"] = "true"
                    classes = setOf("mt-5")
                    chatsNavView(
                        chatService.findChatsByPage(PageRequest.of(0, 16)),
                        msgPair.chat.id.toString()
                    )
                }
            }
        }
        val headers = HttpHeaders()
        headers.add("HX-Push", "/chat/${chatDto.id}")
        //headers.add("HX-Trigger", "chatsEvent")
        headers.add("HX-Trigger-After-Swap", "chatsEvent")

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
                    msgPair.chat.id.toString()
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
        } else renderComponent { div { mainContentView(chat) } }

        return html
    }




    @GetMapping("/chats")
    fun chats(
        @PageableDefault(size = 16) pageable: Pageable,
        @RequestParam chatId:String = "",
    ): String {

        return renderComponent {
            div {
                chatsNavView(
                    chatService.findChatsByPage(pageable ),
                    chatId
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
