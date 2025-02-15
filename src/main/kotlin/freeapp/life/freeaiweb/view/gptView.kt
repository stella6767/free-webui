package freeapp.life.freeaiweb.view

import freeapp.life.freeaiweb.util.path
import freeapp.life.freeaiweb.view.component.chatFormView
import freeapp.life.freeaiweb.view.component.drawerView
import kotlinx.html.*
import java.util.UUID


fun BODY.gptView() {
    header {
        headerView()
    }
    div {
        mainContentView()
    }
    script {
        src = "/js/chat.js"
    }
}



fun DIV.newChatView(){

    div("bg-gray-900 h-[80vh] flex items-center justify-center") {
        div("text-center") {
            h1("text-2xl font-semibold text-white mb-6") { +"""What can I help with?""" }
            div("flex items-stretch justify-center") {
                textArea(classes = "bg-gray-700 w-96 p-3 border border-gray-300 rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500") {
                    placeholder = "Message AI"
                }
                button(classes = "px-4 py-2 bg-gray-700 rounded-r-md hover:bg-gray-500 text-white font-bold flex items-center justify-center") {
                    type = ButtonType.submit
                    svg("w-6 h-6") {
                        attributes["aria-hidden"] = "true"
                        attributes["xmlns"] = "http://www.w3.org/2000/svg"
                        attributes["fill"] = "none"
                        attributes["viewbox"] = "0 0 10 14"
                        path {
                            attributes["stroke"] = "currentColor"
                            attributes["stroke-linecap"] = "round"
                            attributes["stroke-linejoin"] = "round"
                            attributes["stroke-width"] = "2"
                            attributes["d"] = "M5 13V1m0 0L1 5m4-4 4 4"
                        }
                    }
                }
            }
        }
    }

}



fun DIV.msgPairBlockView(
    msg: String,
    uniqueId: String,
    chatId:String,
) {
    id = "msg-pair-${uniqueId}"
    div("flex justify-end") {
        div("bg-[#414158] p-3 rounded-lg max-w-md text-white") { +msg }
    }
    div {
        id = "ai-response-div-${uniqueId}"
        classes = setOf("my-1", "p-1")
    }
    chatIdHiddenView(chatId)
}


fun DIV.mainContentView() {
    classes = setOf("flex", "transition-all", "duration-300")
    drawerView()
    main("flex flex-col flex-1 h-[90vh] xl:px-48 py-6 transition-all duration-300") {
        id = "content"
        section {
            chatAreaView()
        }
        div {
            chatFormView()
        }
    }
}


fun SECTION.chatAreaView() {
    id = "chatArea"
    classes = setOf("flex-1", "p-4", "overflow-y-auto", "space-y-4")
    attributes["hx-on"] = "htmx::afterSwap: this.scrollTop = this.scrollHeight"
    div {
        sseConnectView()
    }
}


fun DIV.sseConnectView(
    clientId:String = ""
){
    val connectionId = clientId.ifEmpty {
        UUID.randomUUID().toString()
    }

    input {
        type = InputType.hidden
        id = "client-id"
        name = "clientId"
        value = connectionId
    }
    div {
        id = "sse-listener"
        attributes["hx-ext"] = "sse"
        attributes["sse-connect"] = "/chat-sse/${connectionId}"
        attributes["sse-swap"] = "ai-response"
        // 의미 없지만..
        //attributes["hx-target"] = "#ai-response-div"
    }
}

fun DIV.chatIdHiddenView(
    chatId:String = "0"
) {
    input {
        type = InputType.hidden
        id = "chat-id-box"
        name = "chatId"
        value = chatId
        attributes["hx-swap-oob"] = "true"
    }
}


fun HEADER.headerView() {

    id = "header"
    classes = setOf("bg-gray-800", "py-4", "px-8", "flex", "items-center", "transition-all", "duration-300")
    drawerToggleBtnView()
    h1 {
        classes = setOf("text-2xl", "font-bold", "text-white", "ml-3")
        +"Kotlin GPT"
    }

}



private fun HEADER.drawerToggleBtnView() {
    div {
        attributes["hx-on:click"] = """
            htmx.toggleClass('#drawer', '-translate-x-full');
            htmx.toggleClass('#header', 'ml-64');                    
            htmx.toggleClass('#content', 'ml-64')                    
        """.trimIndent()

        classes = setOf(
            "hover:bg-[#b4b4b4]",
            "w-10",
            "h-10",
            "flex",
            "items-center",
            "justify-center",
            "rounded",
            "cursor-pointer"
        )
        svg {
            id = "toggleDrawer"
            classes = setOf("w-6", "h-6", "text-gray-800", "dark:text-white")
            attributes["aria-hidden"] = "true"
            attributes["xmlns"] = "http://www.w3.org/2000/svg"
            attributes["fill"] = "currentColor"
            attributes["viewbox"] = "0 0 17 14"
            path {
                attributes["d"] =
                    "M16 2H1a1 1 0 0 1 0-2h15a1 1 0 1 1 0 2Zm0 6H1a1 1 0 0 1 0-2h15a1 1 0 1 1 0 2Zm0 6H1a1 1 0 0 1 0-2h15a1 1 0 0 1 0 2Z"
            }
        }
    }
}







