package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.AiMessageReqDto
import freeapp.life.freeaiweb.dto.ChatReqDto
import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.service.AiService
import freeapp.life.freeaiweb.service.ChatService
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.View
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import org.springframework.web.servlet.view.FragmentsRendering
import java.time.Duration


@Controller
class ChatController(
    private val aiService: AiService,
    private val chatService: ChatService,
) {
    @GetMapping("/")
    fun index(model: Model, response: HttpServletResponse): String {
        return "page/index"
    }

    @GetMapping("/test")
    fun test(): String {
        return "page/test"
    }

    @GetMapping("/multi-update")
    @ResponseBody
    fun test2(): String {

        val html  = """
            <div id="header" hx-swap-oob="true">
                새로운 헤더 내용!
            </div>           
            <div id="content2">
                !!!
            </div>                       
        """.trimIndent()

        return  html
    }


    @HxRequest
    @GetMapping("/multi-update2")
    @ResponseBody
    fun test3(): String {

        val html  = """
            <div id="content" hx-swap-oob="true">
                새로운 콘텐트 내용!
            </div>           
                                  
        """.trimIndent()

        return  html
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
        model.addAttribute("id", chatDto.id)
        model.addAttribute("name", chatDto.name)

        htmxResponse.addTriggerAfterSwap("newChatEvent")
        htmxResponse.pushUrl = "/chat/${chatDto.id}"

        return FragmentsRendering
            .with("component/mainContent")
            .fragment("component/chatIdBox")
            .fragment("component/titleChatBox")
            .fragment("component/chatNavBox")
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
        model.addAttribute("chat", chat)
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

    @HxRequest
    @DeleteMapping("/chat/{id}")
    @ResponseBody
    fun deleteChat(
        @PathVariable id: Long
    ) {
        return chatService.deleteChatById(id)
    }


    @HxRequest
    @PostMapping("/message")
    fun message(
        model: Model,
        chatReqDto: AiMessageReqDto
    ): FragmentsRendering {

        val msgPair =
            aiService.createMessagePair(chatReqDto)

        model.addAttribute("msg", msgPair)

        return FragmentsRendering
            .with("component/msgPair")
            .build()
    }


    @GetMapping("/chat/{id}")
    fun chat(
        model: Model,
        @PathVariable id: Long,
        request: HttpServletRequest,
    ): String {

        val chat =
            chatService.findChatRespById(id)
        model.addAttribute("chat", chat)
        return "page/gpt"
    }


    @HxRequest
    @GetMapping("/chat/{id}")
    fun chatWithHtmx(
        model: Model,
        @PathVariable id: Long,
        request: HttpServletRequest,
    ): FragmentsRendering {

        val chat =
            chatService.findChatRespById(id)

        model.addAllAttributes(mapOf("chat" to chat, "name" to chat.name))

        return FragmentsRendering
            .with("component/mainContent")
            .fragment("component/titleChatBox")
            .build()
    }


    @HxRequest
    @GetMapping("/chats")
    fun chats(
        model: Model,
        @PageableDefault(size = 20) pageable: Pageable,
        @RequestParam chatId: Long = 0,
    ): String {

        val chat = if (chatId != 0L) {
            ChatRespDto.fromEntity(chatService.findChatById(chatId), false)
        } else null
        val chatsByPage = chatService.findChatsByPage(pageable)
        model.addAllAttributes(mapOf("currentChat" to chat, "chats" to chatsByPage))
        return "component/chatsNav"
    }


    @GetMapping(path = ["/chat-sse/{clientId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamChat(
        @PathVariable clientId: String,
    ): SseEmitter {
        return aiService.connectSse(clientId)
    }

    @HxRequest
    @PostMapping("/upload")
    fun uploadFile(
        @RequestParam("file") file: MultipartFile,
        model: Model
    ): String {

        val uploadResponseDto = aiService.addFileToVectorStore(file)
        model.addAttribute("filename", uploadResponseDto.filename)
        return "component/fileResponseView"
    }


}
