package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.service.OllamaService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class SettingController(
    private val ollamaService: OllamaService,
) {

    private val log = KotlinLogging.logger {  }

    @PostMapping("/model")
    fun setModel(){

    }




}
