package freeapp.life.freeaiweb.view

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import kotlinx.html.div
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class ViewTest {

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
