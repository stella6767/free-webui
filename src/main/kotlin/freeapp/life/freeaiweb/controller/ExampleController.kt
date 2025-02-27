package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.AiMessageReqDto
import freeapp.life.freeaiweb.dto.ChatReqDto
import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.service.AiService
import freeapp.life.freeaiweb.service.ChatService
import freeapp.life.freeaiweb.view.*
import freeapp.life.freeaiweb.view.component.chatsNavView
import freeapp.life.freeaiweb.view.component.titleChatView
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.html.div
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.AbstractPersistable_
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.View
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import org.springframework.web.servlet.view.FragmentsRendering


@Controller
class ExampleController(
    private val aiService: AiService,
    private val chatService: ChatService,
) {
    @GetMapping("/")
    fun index(model: Model, response: HttpServletResponse): String {
        return "page/index"
    }

    @HxRequest
    @GetMapping("/")
    fun indexWithHtmx(model: Model, response: HttpServletResponse): FragmentsRendering {

        return FragmentsRendering
            .with("component/newChat")
            .fragment("component/titleChatBox")
            .build()
    }


    @HxRequest
    @PostMapping("/chat")
    fun newChat(model: Model, chatReqDto: AiMessageReqDto, htmxResponse: HtmxResponse): View {

        val msgPair =
            aiService.createMessagePair(chatReqDto)

        val chatDto =
            ChatRespDto.fromEntity(msgPair.chat, true)

        model.addAttribute("chat", chatDto)
        model.addAttribute("chatId", chatDto.id)
        model.addAttribute("chatName", chatDto.name)

        htmxResponse.addTriggerAfterSwap("newChatEvent")
        htmxResponse.pushUrl = "/chat/${chatDto.id}"

        return  FragmentsRendering
            .with("component/mainContent")
            .fragment("component/chatIdBox")
            .fragment("component/titleChatBox")
            .build()
    }



    @HxRequest
    @PutMapping("/chat/{id}")
    fun updateChat(
        @PathVariable id: Long,
        chatReqDto: ChatReqDto,
        model: Model
    ): String {
        val chat =
            chatService.updateChat(id, chatReqDto)
        model.addAttribute(chat)
        return "component/chatNameBox"
    }


    @HxRequest
    @GetMapping("/chat/rename/{id}")
    fun returnRenameView(
        model: Model,
        @PathVariable id: Long,
        @RequestParam name: String,
    ): String {
        model.addAllAttributes(mapOf("id" to id, "name" to name))
        return "component/chatRenameDiv"
    }



    @DeleteMapping("/chat/{id}")
    fun deleteChat(
        @PathVariable id: Long
    ) {
        return chatService.deleteChatById(id)
    }

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
            div {
                mainContentView(chat)
                titleChatView(chat.name)
            }
        }

        return html
    }


    @GetMapping("/chats")
    fun chats(
        @PageableDefault(size = 20) pageable: Pageable,
        @RequestParam chatId: Long = 0,
    ): String {

        val chat = if (chatId != 0L) {
            ChatRespDto.fromEntity(chatService.findChatById(chatId), false)
        } else null

        val chatsByPage = chatService.findChatsByPage(pageable)

        return renderComponentWithoutWrap {
            chatsNavView(
                chatsByPage,
                chat
            )
        }
    }


    @GetMapping(path = ["/chat-sse/{clientId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamChat(
        @PathVariable clientId: String,
    ): SseEmitter {
        return aiService.connectSse(clientId)
    }

}
