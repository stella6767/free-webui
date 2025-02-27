package freeapp.life.freeaiweb.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
class ExampleController(

) {

    @GetMapping("/example")
    fun example(model: Model, response: HttpServletResponse?): String {
        model.addAttribute("model", DemoModel("Hello World"))
        return "example"
    }


    data class DemoModel(
        val text:String
    )


}
