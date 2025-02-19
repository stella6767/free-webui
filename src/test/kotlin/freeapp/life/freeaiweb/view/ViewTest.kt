package freeapp.life.freeaiweb.view

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.entity.MessagePair
import freeapp.life.freeaiweb.view.component.chatRenameView
import freeapp.life.freeaiweb.view.component.sseConnectView
import freeapp.life.freeaiweb.view.component.titleChatView
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime
import kotlin.test.Test

class ViewTest {


    @Test
    fun escapeTest() {

        val name: String = "test"

//        val html = renderComponent {
//            li {
//                attributes["hx-get"] = "/chat/rename/1"
//                attributes["hx-trigger"] = "click"
//                attributes["hx-target"] = "#chat-name-div-1"
//                attributes["hx-swap"] = "outerHTML"
//                //attributes["hx-vals"] = """{"name": "${name}"}"""
//                //attributes["hx-vals"] = "{&quot;name&quot;: &quot;why&quot;}"
//                attributes["hx-vals"] = """js:{"chatId": document.getElementById("chat-id-box").textContent}"""
//                //attributes["hx-vals"] = """js:{"name": document.getElementById("chat-name-div-${chat.id}").value}"""
//
//                span(classes = "block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white") {
//                    +"Rename"
//                }
//            }
//        }
//
//        println(html)
//
//        val html2 = renderComponent {
//            chatRenameView(1, name)
//        }
//
//        println(html2)
//        println(chatRenameView(1, name))

        val component = renderComponent {
            titleChatView("")
        }

        println(component)

    }

    @Test
    fun kotlinHtmlTest() {
//        val htmlContent = createHTML().div {
//            // htmx 관련 속성을 최초 설정 (예: hx-get 속성)
//            attributes["hx-get"] = "/initial-endpoint"
//            classes = setOf("mt-2")
//            // 태그 내용 출력: 이 시점에서 내부 속성이 downstream으로 전달됩니다.
//            + "Loading content..."
//
//            // 이후 hx-get 속성을 변경하려고 시도하면 IllegalStateException 발생
//            //attributes["hx-get"] = "/updated-endpoint"  // 여기서 예외 발생
//            classes = setOf("mt-4")
//            //이미 문자열 다운스트림 전달 후 클래스 수정이니 오류
//        }
//        val htmlContent = renderComponent {
//            mainContentView(ChatRespDto(1, "", mutableListOf() , LocalDateTime.now()))
//        }
//        println(htmlContent)
    }

    @Test
    fun mdBlockTest() {

        val md = """
            # Heading
        
            Text **bold text** *italic text* ~strike~
        
            ```js
            test.foo();
            ```
        
            ```html
            &lt;foo>
            ```
       
            * List item 1
            * List item 2               	
        """.trimIndent()


        val toHtml = markDownToHtml(md)

        println(toHtml)
    }

    fun markDownToHtml(chunk: String): String {

        val parser: Parser = Parser.builder().build()
        val renderer: HtmlRenderer = HtmlRenderer.builder().build()

        val parseString =
            parser.parse(chunk)
        val htmlString =
            renderer.render(parseString)

        return htmlString
    }


}
