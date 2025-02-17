package freeapp.life.freeaiweb.view

import freeapp.life.freeaiweb.util.path
import freeapp.life.freeaiweb.view.component.drawerView
import freeapp.life.freeaiweb.view.component.headerView
import freeapp.life.freeaiweb.view.component.sseConnectView
import kotlinx.html.*


fun BODY.chatInitialView() {
    header {
        headerView()
    }
    div {
        id = "main-container"
        div {
            newChatView()
        }
    }
}


fun DIV.newChatView() {
    id = "content"

    div("bg-gray-900 h-[80vh] flex items-center justify-center") {
        div("text-center") {
            h1("text-2xl font-semibold text-white mb-6") { +"""What can I help with?""" }
            div("flex items-stretch justify-center") {
                attributes["hx-post"] = "/chat"
                attributes["hx-include"] = "#client-id, #chat-input-area"
                attributes["hx-target"] = "#main-container"
                attributes["hx-trigger"] = "keydown[!shiftKey && key=='Enter'] from:#chat-input-area, click from:#chat-init-btn"
                attributes["hx-on--before-request"] = "javascript:document.querySelector('#header').classList.remove('ml-64')"

                textArea(classes = "bg-gray-700 w-96 p-3 border border-gray-300 rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500") {
                    id = "chat-input-area"
                    name = "msg"
                    placeholder = "Message AI"
                    required = true
                }
                button(classes = "px-4 py-2 bg-gray-700 rounded-r-md hover:bg-gray-500 text-white font-bold flex items-center justify-center") {
                    id = "chat-init-btn"
                    type = ButtonType.submit
                    svg("w-6 h-6") {
                        attributes["aria-hidden"] = "true"
                        //attributes["xmlns"] = "http://www.w3.org/2000/svg"
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
