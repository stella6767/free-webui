package freeapp.life.freeaiweb.view

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.stream.createHTML

fun renderPageWithLayout(bodyContent: DIV.() -> Unit): String {
    return writePage {
        defaultHeader()
        body {
            attributes["xmlns:hx-on"] = "http://www.w3.org/1999/xhtml"
            //navbar()

            defaultBody {
                bodyContent()
            }

            defaultFooter()
        }
    }
}


fun renderComponent(div: DIV.() -> Unit): String {
    return createHTML().div {
        div()
    }
}

inline fun writePage(crossinline block: HTML.() -> Unit): String {
    return createHTMLDocument().html {
        visit(block)
    }.serialize()
}

fun BODY.defaultBody(content: DIV.() -> Unit) {

    div {
        id = "content-body"
        classes = setOf("py-3")
        //progressView()
        div {
            id = "toast"
        }
        content()
        //loginModalView()
    }
}


private fun HTML.defaultHeader() {
    head {
        script {
            src = "webjars/htmx.org/2.0.4/htmx.min.js"
        }
        link {
            rel = "stylesheet"
            href = "webjars/tailwindcss/4.0.0/tailwind.min.css"
        }

        script {
            src = "/js/client.js"
            defer = true
        }

        meta {
            httpEquiv = "Content-Type"
            content = "text/html; charset=UTF-8"
        }
        meta { charset = "UTF-8" }
        meta(name = "author", content = "stella6767")
        meta(name = "keywords", content = arrayOf("Kotlin", "htmx").joinToString(","))
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")


        style {
            unsafe {
                raw(
                    """
                        @media (prefers-color-scheme:dark){
                            body{
                                color:white;
                                background:white;
                            }
                        }
                       """
                )
            }
        }

    }
}


fun BODY.defaultFooter() {

    footer("footer footer-center p-4 bg-base-300 text-base-content ") {
        id = "footer"

        div {
            p { +"""Created by Stella6767""" }
            a {
                href = "https://github.com/stella6767"
                target = "_blank"
                style = "text-decoration: none;"
                +"""GitHub"""
            }
        }
    }
}

