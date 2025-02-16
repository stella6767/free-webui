package freeapp.life.freeaiweb.view

import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.MessagePair
import freeapp.life.freeaiweb.util.path
import freeapp.life.freeaiweb.view.component.chatFormView
import freeapp.life.freeaiweb.view.component.drawerView
import freeapp.life.freeaiweb.view.component.headerView
import freeapp.life.freeaiweb.view.component.sseConnectView
import kotlinx.html.*
import java.util.UUID


fun BODY.chatView(chat: ChatRespDto) {
    header {
        headerView()
    }
    div {
        mainContentView(chat)
    }
    script {
        src = "/js/chat.js"
    }
}


fun DIV.msgPairBlockView(
    messagePair: MessagePair,
    chatId: String,
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
        +aiMsg
    }
}


fun DIV.mainContentView(chat: ChatRespDto) {
    classes = setOf("flex", "transition-all", "duration-300")
    attributes["hx-push-url"] = "/chat/${chat.id}"
    drawerView()
    main("flex flex-col flex-1 h-[90vh] xl:px-48 py-6 transition-all duration-300") {
        id = "content"
        section {
            chatAreaView(chat)
        }
        div {
            chatIdHiddenView(chat.id.toString())
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
                chat.id.toString()
            )
            //todo chatid tag 를 하나만 두도록
        }
    }

}


fun DIV.chatIdHiddenView(
    chatId: String,
) {
    input {
        type = InputType.hidden
        id = "chat-id-box"
        name = "chatId"
        value = chatId
        attributes["hx-swap-oob"] = "true"
    }
}






