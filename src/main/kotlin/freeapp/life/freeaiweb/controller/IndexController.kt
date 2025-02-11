package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.view.indexView
import freeapp.life.freeaiweb.view.renderPageWithLayout
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class IndexController(

) {

    private val log = KotlinLogging.logger {  }

    @GetMapping("/test")
    fun test(): String {
        return renderPageWithLayout {
            indexView()
        }
    }



}
