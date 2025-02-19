package freeapp.life.freeaiweb.view

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.view.component.chatNameBoxView
import freeapp.life.freeaiweb.view.component.titleChatView
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.stream.appendHTML
import kotlinx.html.stream.createHTML
import org.springframework.ai.ollama.api.OllamaOptions
import java.time.LocalDateTime

import kotlin.test.Test

class ViewTest {

    val chat =
        ChatRespDto(1, "", mutableListOf(), LocalDateTime.now())


    @Test
    fun optionTest(){
        val options = OllamaOptions()

        println(options.format)
        println(options.temperature)
        println(options.topK)
        println(options.topP)

    }

    @Test
    fun downstreamTest() {

        val renderComponent = renderComponent {
            div {
                mainContentView(chat)
                chatIdHiddenView(1)
                titleChatView("??")
            }
        }

        println(renderComponent)

    }

    @Test
    fun escapeTest() {

        val name: String = "test"



        val html = createHTML().div {
            chatNameBoxView(chat)
        }

        val html2 = buildString {
            appendHTML().div { chatNameBoxView(chat) }
        }

        val html3 = renderComponent {
            div {
                chatNameBoxView(chat)
            }
        }

        val html4 = renderComponentWithoutWrap {
            chatNameBoxView(chat)
        }

        println(html)
        println(html2)
        println(html3)
        println(html4)
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
