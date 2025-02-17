package freeapp.life.freeaiweb.view.component

import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.util.path
import freeapp.life.freeaiweb.view.chatIdHiddenView

import kotlinx.html.*
import org.springframework.data.domain.Page
import java.util.*

fun DIV.drawerView() {
    aside {
        id = "drawer"
        classes = setOf(
            "fixed",
            "top-0",
            "left-0",
            "h-full",
            "w-64",
            "bg-gray-800",
            "p-4",
            "transform",
            "transition-transform",
            "duration-300",
            "z-10"
        )
        newChatBtnView()
        div {
            id = "chat-nav-box"
            attributes["hx-get"] = "/chats"
            attributes["hx-trigger"] = "load, chatsEvent from:body"
            attributes["hx-vals"] = """js:{"chatId": window.location.pathname.split("/").pop()}"""

            classes = setOf("mt-5")
        }

    }
}


fun DIV.chatsNavView(chats: Page<ChatRespDto>, chatId:String = "") {
    nav {
        id = "chat-nav-list"
        ul {
            classes = setOf("space-y-2")
            for (chat in chats) {
                li("truncate group cursor-pointer tab-active hover:bg-[#b4b4b480] ${if (chatId == chat.id.toString()) "bg-[#b4b4b480]" else ""}") {
                    id = "chat-li-${chat.id}"
                    attributes["hx-trigger"] = "click"
                    attributes["hx-get"] = "/chat/${chat.id}"
                    attributes["hx-target"] = "#main-container"
                    attributes["hx-push-url"] = "true"
                    attributes["hx-on--after-on-load"] =
                        """
                               let currentTab = document.querySelector('.bg-\\[\\#b4b4b480\\]');
                               if(currentTab) {
                                 currentTab.classList.remove('bg-[#b4b4b480]');
                               }
                               let newTab = event.target                  
                               newTab.classList.add('bg-[#b4b4b480]')                            
                            """.trimIndent()


                    span("text-gray-300 hover:text-white") {
                        +chat.name
                    }

                    //todo 해당 chat 선택하면, chat이 색상 변한 상태로 유지
                    //todo chat rename 시 수정란이 즉시 그 ui에 표시
                    //todo kotlin gpt 제목 옆에 현재 chat 제목표시
                    //todo 모델 설정 드랍다운 메뉴
                    //TODO 아래 깃허브 로고
                    //todo readme 작성
                    //todo 도커 이미지 굽고 허브에 올리기
                    //todo 로딩 표시 및 인피니트 스크롤 페이징 처리
                    //todo 일어날때마다 사이드바 트리거.
                    //exe 파일 추출

                    div("absolute right-2 transform -translate-y-1/2 opacity-0 group-hover:opacity-100 transition-opacity duration-200 cursor-pointer") {
                        id = "dropdownHoverButton-${chat.id}"
                        attributes["data-dropdown-toggle"] = "dropdownHover-${chat.id}"
                        attributes["data-dropdown-trigger"] = "hover"
                        svg("icon-md") {
                            attributes["width"] = "24"
                            attributes["height"] = "24"
                            attributes["viewbox"] = "0 0 24 24"
                            attributes["fill"] = "none"
                            //attributes["xmlns"] = "http://www.w3.org/2000/svg"
                            path {
                                attributes["fill-rule"] = "evenodd"
                                attributes["clip-rule"] = "evenodd"
                                attributes["d"] =
                                    "M3 12C3 10.8954 3.89543 10 5 10C6.10457 10 7 10.8954 7 12C7 13.1046 6.10457 14 5 14C3.89543 14 3 13.1046 3 12ZM10 12C10 10.8954 10.8954 10 12 10C13.1046 10 14 10.8954 14 12C14 13.1046 13.1046 14 12 14C10.8954 14 10 13.1046 10 12ZM17 12C17 10.8954 17.8954 10 19 10C20.1046 10 21 10.8954 21 12C21 13.1046 20.1046 14 19 14C17.8954 14 17 13.1046 17 12Z"
                                attributes["fill"] = "currentColor"
                            }
                        }
                    }
                    div("z-30 hidden bg-white divide-y divide-gray-100 rounded-lg shadow-sm w-44 dark:bg-gray-700") {
                        id = "dropdownHover-${chat.id}"
                        ul("py-2 text-sm text-gray-700 dark:text-gray-200") {
                            attributes["aria-labelledby"] = "dropdownHoverButton-${chat.id}"
                            li {
                                span(classes = "block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white") {
                                    +"Rename"
                                }
                            }
                            li {
                                span(classes = "block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white") {
                                    +"Delete"
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}


private fun ASIDE.newChatBtnView() {

    div("flex items-center cursor-pointer font-sans text-base font-medium h-11 leading-5 pl-2.5 pr-2.5 break-words text-[#f8fafc] hover:bg-[#b4b4b4]") {

        attributes["hx-trigger"] = "click"
        attributes["hx-get"] = "/"
        attributes["hx-target"] = "#main-container"
        attributes["hx-push-url"] = "true"


        div("mr-2") {
            svg("w-7 h-7") {
                attributes["viewbox"] = "0 0 28 28"
                attributes["fill"] = "none"
                //attributes["xmlns"] = "http://www.w3.org/2000/svg"
                path {
                    attributes["d"] =
                        "M9.10999 27C8.92999 27 8.76001 26.96 8.60001 26.9C8.43001 26.83 8.29 26.74 8.16 26.61C8.03 26.49 7.94 26.3499 7.87 26.1899C7.79999 26.0299 7.76001 25.8599 7.76001 25.6899L7.73001 23.04C7.34001 22.98 6.95001 22.8799 6.57001 22.7599C6.19001 22.6299 5.83001 22.48 5.48001 22.29C5.13001 22.1 4.79999 21.88 4.48999 21.63C4.17999 21.39 3.89 21.1199 3.63 20.82C3.37 20.52 3.13999 20.21 2.92999 19.87C2.72999 19.53 2.56001 19.18 2.42001 18.82C2.28001 18.45 2.17001 18.07 2.10001 17.69C2.03001 17.3 2 16.92 2 16.53V9.46995C2 9.03995 2.04 8.61995 2.12 8.19995C2.21 7.77995 2.34 7.36995 2.5 6.96995C2.67 6.57995 2.88 6.19995 3.12 5.84995C3.36 5.48995 3.64001 5.15995 3.95001 4.85995C4.26001 4.55995 4.59999 4.28995 4.95999 4.04995C5.32999 3.80995 5.70999 3.60995 6.10999 3.44995C6.51999 3.27995 6.94 3.15995 7.37 3.07995C7.79999 2.98995 8.23001 2.94995 8.67001 2.94995H13.3C13.46 2.94995 13.61 2.97995 13.76 3.03995C13.9 3.09995 14.03 3.17995 14.14 3.28995C14.25 3.39995 14.33 3.51995 14.39 3.65995C14.45 3.79995 14.48 3.94995 14.48 4.09995C14.48 4.25995 14.45 4.39995 14.39 4.54995C14.33 4.68995 14.25 4.80995 14.14 4.91995C14.03 5.02995 13.9 5.10995 13.76 5.16995C13.61 5.22995 13.46 5.25995 13.3 5.25995H8.67001C8.38001 5.25995 8.09999 5.27995 7.82999 5.33995C7.54999 5.38995 7.27999 5.46995 7.01999 5.57995C6.75999 5.67995 6.50999 5.80995 6.26999 5.96995C6.03999 6.11995 5.82 6.29995 5.62 6.48995C5.42 6.68995 5.23999 6.89995 5.07999 7.12995C4.92999 7.35995 4.78999 7.59995 4.67999 7.85995C4.57999 8.10995 4.49 8.37995 4.44 8.64995C4.38 8.91995 4.35999 9.18995 4.35999 9.46995V16.53C4.35999 16.81 4.38 17.08 4.44 17.36C4.5 17.63 4.58 17.9 4.69 18.16C4.8 18.42 4.93 18.67 5.09 18.9C5.25 19.13 5.43001 19.3499 5.64001 19.5499C5.84001 19.75 6.05999 19.92 6.29999 20.08C6.53999 20.24 6.79 20.37 7.06 20.47C7.32 20.58 7.6 20.66 7.88 20.72C8.16001 20.77 8.44001 20.7999 8.73001 20.7999C8.91001 20.7999 9.08 20.83 9.25 20.9C9.41 20.97 9.55999 21.0599 9.67999 21.18C9.80999 21.3099 9.91001 21.45 9.98001 21.61C10.05 21.77 10.08 21.94 10.09 22.11L10.1 23.74L13.08 21.61C13.84 21.07 14.69 20.7999 15.63 20.7999H19.32C19.61 20.7999 19.89 20.77 20.16 20.72C20.44 20.67 20.71 20.59 20.97 20.4799C21.23 20.3699 21.48 20.24 21.72 20.09C21.95 19.94 22.17 19.76 22.37 19.57C22.57 19.3699 22.75 19.16 22.91 18.93C23.07 18.7 23.2 18.46 23.31 18.2C23.41 17.95 23.5 17.68 23.55 17.41C23.61 17.14 23.63 16.87 23.63 16.59V12.94C23.63 12.79 23.66 12.64 23.72 12.5C23.78 12.36 23.87 12.23 23.98 12.13C24.09 12.02 24.22 11.93 24.36 11.88C24.51 11.82 24.66 11.79 24.82 11.79C24.97 11.79 25.12 11.82 25.27 11.88C25.41 11.93 25.54 12.02 25.65 12.13C25.76 12.23 25.85 12.36 25.91 12.5C25.97 12.64 26 12.79 26 12.94V16.59C26 17.02 25.95 17.44 25.87 17.86C25.78 18.28 25.66 18.69 25.49 19.08C25.32 19.48 25.11 19.8499 24.87 20.2099C24.63 20.57 24.35 20.9 24.04 21.2C23.73 21.5 23.39 21.7699 23.03 22.0099C22.67 22.2499 22.28 22.45 21.88 22.61C21.47 22.77 21.06 22.9 20.63 22.9799C20.2 23.07 19.76 23.11 19.32 23.11H16.4C15.47 23.11 14.62 23.3799 13.86 23.9199L9.91 26.74C9.67 26.91 9.39999 27 9.10999 27Z"
                    attributes["fill"] = "currentColor"
                }
                path {
                    attributes["d"] =
                        "M24.6805 5.14453H18.1874C17.5505 5.14453 17.0342 5.66086 17.0342 6.29778C17.0342 6.9347 17.5505 7.45102 18.1874 7.45102H24.6805C25.3175 7.45102 25.8338 6.9347 25.8338 6.29778C25.8338 5.66086 25.3175 5.14453 24.6805 5.14453Z"
                    attributes["fill"] = "currentColor"
                }
                path {
                    attributes["d"] =
                        "M22.6137 3.1804C22.6137 2.52848 22.0852 2 21.4333 2C20.7814 2 20.2529 2.52848 20.2529 3.1804V9.4168C20.2529 10.0687 20.7814 10.5972 21.4333 10.5972C22.0852 10.5972 22.6137 10.0687 22.6137 9.4168V3.1804Z"
                    attributes["fill"] = "currentColor"
                }
            }
        }
        +"""New chat"""
    }
}

fun DIV.chatFormView() {
    classes = setOf("border-t", "border-gray-700", "p-4")
    form {
        id = "chatForm"
        classes = setOf("flex")
        attributes["hx-post"] = "/message"
        attributes["hx-include"] = "#client-id, #chat-id-box"
        attributes["hx-target"] = "#chatArea"
        attributes["hx-swap"] = "beforeend"
        attributes["hx-trigger"] = "keydown[!shiftKey && key=='Enter'] from:#chatInput, click from:#chat-input-btn"
        attributes["hx-on--after-request"] = "javascript:document.getElementById('chatInput').value = ''"

        textArea {
            id = "chatInput"
            name = "msg"
            attributes["placeholder"] = "Message AI"
            attributes["autocomplete"] = "off"
            attributes["rows"] = "3"
            required = true
            classes = setOf(
                "flex-1",
                "px-4",
                "py-2",
                "rounded-l-md",
                "bg-gray-700",
                "border",
                "border-gray-600",
                "focus:outline-none",
                "focus:ring-2",
                "focus:ring-blue-500",
                "text-white"
            )
        }
        button {
            id = "chat-input-btn"
            type = ButtonType.submit
            classes = setOf(
                "px-4",
                "py-2",
                "bg-gray-700",
                "rounded-r-md",
                "hover:bg-gray-500",
                "text-white",
                "font-bold",
                "flex",
                "items-center",
                "justify-center"
            )
            svg {
                classes = setOf("w-6", "h-6")
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


fun HEADER.headerView() {

    id = "header"
    classes = setOf("bg-gray-800", "py-4", "px-8", "flex", "items-center", "transition-all", "duration-300", "ml-64")
    drawerToggleBtnView()
    h1 {
        id = "header-title"
        classes = setOf("text-2xl", "font-bold", "text-white", "ml-3", "cursor-pointer")
        attributes["onclick"] = "window.location.href='/'"
        +"Kotlin GPT"
    }
    div {
        drawerView()
    }
    div {
        sseConnectView()
    }
}



fun HEADER.drawerToggleBtnView() {
    div {
        attributes["hx-on:click"] = """
            htmx.toggleClass('#drawer', '-translate-x-full');
            htmx.toggleClass('#header', 'ml-64');                    
            htmx.toggleClass('#main-container', 'ml-64')                    
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
            //attributes["xmlns"] = "http://www.w3.org/2000/svg"
            attributes["fill"] = "currentColor"
            attributes["viewbox"] = "0 0 17 14"
            path {
                attributes["d"] =
                    "M16 2H1a1 1 0 0 1 0-2h15a1 1 0 1 1 0 2Zm0 6H1a1 1 0 0 1 0-2h15a1 1 0 1 1 0 2Zm0 6H1a1 1 0 0 1 0-2h15a1 1 0 0 1 0 2Z"
            }
        }
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
        attributes["hx-swap-oob"] = "true"
        // 의미 없지만..
        //attributes["hx-target"] = "#ai-response-div"
    }
}
