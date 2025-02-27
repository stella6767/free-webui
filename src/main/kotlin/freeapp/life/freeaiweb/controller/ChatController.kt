//package freeapp.life.freeaiweb.controller
//
//import freeapp.life.freeaiweb.dto.AiMessageReqDto
//import freeapp.life.freeaiweb.dto.ChatReqDto
//import freeapp.life.freeaiweb.dto.ChatRespDto
//import freeapp.life.freeaiweb.service.AiService
//import freeapp.life.freeaiweb.service.ChatService
//import freeapp.life.freeaiweb.view.*
//import freeapp.life.freeaiweb.view.component.chatNameBoxView
//import freeapp.life.freeaiweb.view.component.chatRenameView
//import freeapp.life.freeaiweb.view.component.chatsNavView
//import freeapp.life.freeaiweb.view.component.titleChatView
//import jakarta.servlet.http.HttpServletRequest
//import kotlinx.html.div
//import kotlinx.html.id
//import mu.KotlinLogging
//import org.springframework.data.domain.Pageable
//import org.springframework.data.web.PageableDefault
//import org.springframework.http.HttpHeaders
//import org.springframework.http.HttpStatus
//import org.springframework.http.MediaType
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
//
//
//@RestController
//class ChatController(
//    private val aiService: AiService,
//    private val chatService: ChatService,
//) {
//
//    private val log = KotlinLogging.logger { }
//
//    @GetMapping("/")
//    fun index(
//        request: HttpServletRequest,
//    ): ResponseEntity<String> {
//
//        val html = if (request?.getHeader("HX-Request") == null) {
//            renderPageWithLayout { chatInitialView() }
//        } else renderComponent {
//            div {
//                newChatView()
//                titleChatView("")
//            }
//        }
//
//        val headers = HttpHeaders()
//        return ResponseEntity(html, headers, HttpStatus.OK)
//    }
//
//    @PostMapping("/chat")
//    fun newChat(chatReqDto: AiMessageReqDto): ResponseEntity<String> {
//
//        val msgPair =
//            aiService.createMessagePair(chatReqDto)
//
//        val chatDto =
//            ChatRespDto.fromEntity(msgPair.chat, true)
//
//        val renderComponent = renderComponent {
//            div {
//                mainContentView(chatDto)
//                chatIdHiddenView(chatDto.id)
//                titleChatView(chatDto.name)
//            }
//        }
//
//        val headers = HttpHeaders()
//        headers.add("HX-Push", "/chat/${chatDto.id}")
//        headers.add("HX-Trigger-After-Swap", "newChatEvent")
//
//        return ResponseEntity(renderComponent, headers, HttpStatus.OK)
//    }
//
//    @PutMapping("/chat/{id}")
//    fun updateChat(
//        @PathVariable id: Long,
//        chatReqDto: ChatReqDto
//    ): String {
//        val chat =
//            chatService.updateChat(id, chatReqDto)
//
//        val html = renderComponentWithoutWrap {
//            chatNameBoxView(chat)
//        }
//
//        return html
//    }
//
//
//    @GetMapping("/chat/rename/{id}")
//    fun returnRenameView(
//        @PathVariable id: Long,
//        @RequestParam name: String,
//    ): String {
//
//        return chatRenameView(id, name)
//    }
//
//
//    @DeleteMapping("/chat/{id}")
//    fun deleteChat(
//        @PathVariable id: Long
//    ) {
//
//        return chatService.deleteChatById(id)
//    }
//
//
//    @PostMapping("/message")
//    fun message(chatReqDto: AiMessageReqDto): String {
//
//        val msgPair =
//            aiService.createMessagePair(chatReqDto)
//
//        val userChatView = renderComponent {
//            div {
//                msgPairBlockView(
//                    msgPair,
//                )
//            }
//        }
//        return userChatView
//    }
//
//
//    @GetMapping("/chat/{id}")
//    fun chat(
//        @PathVariable id: Long,
//        request: HttpServletRequest,
//    ): String {
//
//        val chat =
//            chatService.findChatRespById(id)
//
//        val html = if (request?.getHeader("HX-Request") == null) {
//            renderPageWithLayout {
//                chatView(chat)
//            }
//        } else renderComponent {
//            div {
//                mainContentView(chat)
//                titleChatView(chat.name)
//            }
//        }
//
//        return html
//    }
//
//
//    @GetMapping("/chats")
//    fun chats(
//        @PageableDefault(size = 20) pageable: Pageable,
//        @RequestParam chatId: Long = 0,
//    ): String {
//
//        val chat = if (chatId != 0L) {
//            ChatRespDto.fromEntity(chatService.findChatById(chatId), false)
//        } else null
//
//        val chatsByPage = chatService.findChatsByPage(pageable)
//
//        return renderComponentWithoutWrap {
//            chatsNavView(
//                chatsByPage,
//                chat
//            )
//        }
//    }
//
//
//    @GetMapping(path = ["/chat-sse/{clientId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
//    fun streamChat(
//        @PathVariable clientId: String,
//    ): SseEmitter {
//        return aiService.connectSse(clientId)
//    }
//
//
//}
