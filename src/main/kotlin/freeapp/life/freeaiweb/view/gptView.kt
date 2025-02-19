package freeapp.life.freeaiweb.view

import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.entity.MessagePair
import freeapp.life.freeaiweb.view.component.chatFormView
import freeapp.life.freeaiweb.view.component.headerView
import freeapp.life.freeaiweb.view.component.sseConnectView
import kotlinx.html.*


fun BODY.chatView(chat: ChatRespDto) {
    header {
        headerView(chat)
    }
    div {
        id = "main-container"
        classes = setOf("ml-64")
        div {
            mainContentView(chat)
        }
        div {
            sseConnectView()
        }
    }


}


fun DIV.msgPairBlockView(
    messagePair: MessagePair,
) {
    val humanMsg = messagePair.humanMessage.content
    val aiMsg = messagePair.aiMessage?.content ?: ""

    id = "msg-pair-${messagePair.id}"
    div("flex justify-end") {
        div("bg-[#414158] p-3 rounded-lg max-w-md text-white") { +humanMsg }
    }
    div {
        id = "ai-response-div-${messagePair.id}"
        classes = setOf("my-1", "p-1")
        unsafe {
          raw(aiMsg)
        }
    }
}


fun DIV.mainContentView(chat: ChatRespDto) {
    id = "content"
    classes = setOf("flex", "transition-all", "duration-300")

    main("flex flex-col flex-1 h-[90vh] xl:px-48 py-6 transition-all duration-300") {
        section {
            chatAreaView(chat)
        }

        div {
            chatFormView()
        }

    }
}


fun SECTION.chatAreaView(
    chat: ChatRespDto
) {
    id = "chatArea"
    classes = setOf("flex-1", "p-4", "overflow-y-auto", "space-y-4")
    attributes["hx-on"] = "htmx::afterSwap: this.scrollTop = this.scrollHeight"

    for (pair in chat.messagePairs) {
        div {
            msgPairBlockView(
                pair,
            )
        }
    }

}


fun FlowContent.chatIdHiddenView(
    chatId: Long,
) {
    input {
        type = InputType.hidden
        id = "chat-id-box"
        name = "chatId"
        value = chatId.toString()
        attributes["hx-swap-oob"] = "true"
    }
}






