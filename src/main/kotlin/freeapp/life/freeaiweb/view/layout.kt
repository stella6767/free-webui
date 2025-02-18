package freeapp.life.freeaiweb.view

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.stream.createHTML

fun renderPageWithLayout(bodyContent: BODY.() -> Unit): String {
    return writePage {
        defaultHeader()
        body {
            attributes["xmlns:hx-on"] = "http://www.w3.org/1999/xhtml"
            id = "content-body"
            classes = setOf("bg-gray-900", "text-gray-200", "font-sans")
            bodyContent()
        }
    }
}

fun renderComponent(block: TagConsumer<String>.() -> Unit): String {
    return createHTML().apply(block).finalize()
}

inline fun writePage(crossinline block: HTML.() -> Unit): String {
    return createHTMLDocument().html {
        visit(block)
    }.serialize()
}




private fun HTML.defaultHeader() {
    head {
        script {
            src = "/js/htmx.min.js"
            defer = true
        }
        script {
            src = "/js/sse.js"
            defer = true
        }
        script {
            src = "/js/custom-htmx.js"
        }
        script {
            src = "/js/tailwind.min.js"
            defer = true
        }
        script {
            src = "/js/flowbite.min.js"
            defer = true
        }
        link {
            rel = "stylesheet"
            href = "/css/flowbite.min.css"
        }

        meta {
            httpEquiv = "Content-Type"
            content = "text/html; charset=UTF-8"
        }
        meta { charset = "UTF-8" }
        meta(name = "author", content = "stella6767")
        meta(name = "keywords", content = arrayOf("Kotlin", "htmx").joinToString(","))
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")

    }
}


