package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.service.OllamaService
import mu.KotlinLogging
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
class SettingController(
    private val ollamaService: OllamaService,
    private val chatModel: OllamaChatModel
) {

    private val log = KotlinLogging.logger { }


    @GetMapping("/model")
    fun model(): Flux<String> {

//        val call = chatModel.call("requestRto.prompt")
        val stream = chatModel.stream("hi")

        return stream
    }

    @PostMapping("/model")
    fun setModel() {

    }


}
