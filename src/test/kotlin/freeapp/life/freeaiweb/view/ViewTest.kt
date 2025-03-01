package freeapp.life.freeaiweb.view

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import freeapp.life.freeaiweb.dto.ChatRespDto

import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.TemplateOutput
import gg.jte.output.StringOutput
import gg.jte.resolve.DirectoryCodeResolver
import org.instancio.Instancio
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.time.LocalDateTime
import kotlin.io.path.Path
import kotlin.test.Test


class ViewTest {

    val chat =
        ChatRespDto(1, "", mutableListOf(), LocalDateTime.now())

    val chats: List<ChatRespDto> =
        Instancio.ofList(ChatRespDto::class.java).size(1).create()

    var pageRequest = PageRequest.of(0, 10)

    var page: Page<ChatRespDto> =
        PageImpl(chats, pageRequest, chats.size.toLong())


    @Test
    fun jteViewTest(){
        val templateEngine = TemplateEngine.create(
            DirectoryCodeResolver(Path("src/main/jte/")),
            ContentType.Html
        )
        val output: TemplateOutput = StringOutput()
        templateEngine.render("page/test.jte", mapOf(), output)
        println(output.toString())
    }




    @Test
    fun optionTest(){
        val options = OllamaOptions()

        println(options.format)
        println(options.temperature)
        println(options.topK)
        println(options.topP)

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
