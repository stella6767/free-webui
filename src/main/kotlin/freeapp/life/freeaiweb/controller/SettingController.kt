package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.OllamaRequestRto
import freeapp.life.freeaiweb.service.OllamaService
import freeapp.life.freeaiweb.view.component.setModelFormView
import freeapp.life.freeaiweb.view.renderComponentWithoutWrap
import mu.KotlinLogging
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class SettingController(
    private val ollamaService: OllamaService,
    private val chatModel: OllamaChatModel
) {

    private val log = KotlinLogging.logger { }


    @GetMapping("/setting")
    fun getSetting(): String {

        val modelSetting =
            ollamaService.getModelSetting()

        return renderComponentWithoutWrap {
            setModelFormView(modelSetting)
        }
    }

    @PutMapping("/setting")
    fun updateSetting(ollamaRequestRto: OllamaRequestRto) {

        ollamaService.updateChatClient(ollamaRequestRto)
    }


}
