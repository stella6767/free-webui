package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.AiMessageReqDto
import freeapp.life.freeaiweb.service.AiService
import freeapp.life.freeaiweb.service.ChatService
import freeapp.life.freeaiweb.view.component.chatsNavView
import freeapp.life.freeaiweb.view.msgPairBlockView
import freeapp.life.freeaiweb.view.gptView
import freeapp.life.freeaiweb.view.renderComponent
import freeapp.life.freeaiweb.view.renderPageWithLayout
import mu.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter


@RestController
class IndexController(
    private val aiService: AiService,
    private val chatService: ChatService,
) {

    private val log = KotlinLogging.logger {  }


    @GetMapping("/test")
    fun test(): String {
        return renderPageWithLayout {
            gptView()
        }
    }


//    @PostMapping("/chat")
//    fun newChat(chatReqDto: AiMessageReqDto): String {
//
//        val uniqueId =
//            aiResponseDivId.incrementAndGet()
//
//        aiService.sendAiResponse(chatReqDto, uniqueId)
//
//        val userChatView = renderComponent {
//            chatMsgView(
//                chatReqDto.msg,
//                uniqueId
//            )
//        }
//
//        return userChatView
//    }



    @GetMapping("/chats")
    fun chats(
        @PageableDefault(size = 16) pageable: Pageable,
    ): String {

        return renderComponent {
            chatsNavView(chatService.findChatsByPage(pageable))
        }
    }


    @GetMapping("/chat/{id}")
    fun chat(
        @PathVariable id: Long,
    ): String {

        val chat =
            chatService.findChatById(id)
        chat.messagePairs

//        return renderComponent {
//            chatsNavView(chatService.findChatsByPage(pageable))
//        }

        TODO()
    }




    @PostMapping("/chat")
    fun chat(chatReqDto: AiMessageReqDto): String {

        val msgPair =
            aiService.createMessagePair(chatReqDto)

        val userChatView = renderComponent {
            msgPairBlockView(
                chatReqDto.msg,
                msgPair.id.toString(),
                msgPair.chat.id.toString()
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
