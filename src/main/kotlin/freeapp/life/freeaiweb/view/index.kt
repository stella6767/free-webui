package freeapp.life.freeaiweb.view

import freeapp.life.freeaiweb.util.g
import freeapp.life.freeaiweb.util.path
import kotlinx.html.*


fun BODY.indexView() {
    header {
        headerView()
    }
    div {
        mainContentView()
    }
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

    div("flex") {
        div("bg-gray-600 p-3 rounded-lg max-w-md text-white") { +"""안녕하세요. 무엇을 도와드릴까요?""" }
    }
    div("flex justify-end") {
        div("bg-blue-600 p-3 rounded-lg max-w-md text-white") { +"""지금 상태를 보여주세요.""" }
    }
}

fun HEADER.headerView() {

    id = "header"
    classes = setOf("bg-gray-800", "py-4", "px-8", "flex", "items-center", "transition-all", "duration-300")


    div {
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
    h1 {
        classes = setOf("text-2xl", "font-bold", "text-white", "ml-3")
        +"ChatGPT-like UI"
    }

}


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
            "-translate-x-full",
            "transition-transform",
            "duration-300",
            "z-10"
        )
        svg {
            attributes["xmlns"] = "http://www.w3.org/2000/svg"
            attributes["fill"] = "none"
            attributes["stroke"] = "currentColor"
            attributes["viewbox"] = "0 0 24 24"
            classes = setOf("w-9", "h-9", "humbleicons", "mb-3", "hi-folder-add")
            g {
                attributes["xmlns"] = "http://www.w3.org/2000/svg"
                attributes["stroke"] = "currentColor"
                attributes["stroke-width"] = "2"
                path {
                    attributes["stroke-linejoin"] = "round"
                    attributes["d"] =
                        "M3 18V6a2 2 0 012-2h4.539a2 2 0 011.562.75L12.2 6.126a1 1 0 00.78.375H20a1 1 0 011 1V18a1 1 0 01-1 1H4a1 1 0 01-1-1z"
                }
                path {
                    attributes["stroke-linecap"] = "round"
                    attributes["d"] = "M9.5 12.5h5M12 15v-5"
                }
            }
        }
        nav {
            ul {
                classes = setOf("space-y-2")
                li {
                    a {
                        href = "#"
                        classes = setOf("text-gray-300", "hover:text-white")
                        +"대시보드"
                    }
                }
                li {
                    a {
                        href = "#"
                        classes = setOf("text-gray-300", "hover:text-white")
                        +"모델 관리"
                    }
                }
                li {
                    a {
                        href = "#"
                        classes = setOf("text-gray-300", "hover:text-white")
                        +"설정"
                    }
                }
            }
        }
    }

}


fun DIV.chatFormView() {

    classes = setOf("border-t", "border-gray-700", "p-4")

    form {
        id = "chatForm"
        classes = setOf("flex")
        textArea {
            id = "chatInput"
            attributes["placeholder"] = "메시지를 입력하세요..."
            attributes["autocomplete"] = "off"
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
            attributes["rows"] = "3"
        }
        button {
            type = ButtonType.submit
            classes = setOf(
                "px-4",
                "py-2",
                "bg-gray-700",
                "rounded-r-md",
                "hover:bg-blue-700",
                "text-white",
                "font-bold",
                "flex",
                "items-center",
                "justify-center"
            )
            svg {
                classes = setOf("w-6", "h-6")
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

