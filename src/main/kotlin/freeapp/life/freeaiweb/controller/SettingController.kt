package freeapp.life.freeaiweb.controller

import freeapp.life.freeaiweb.dto.OllamaRequestRto
import freeapp.life.freeaiweb.service.OllamaService
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class SettingController(
    private val ollamaService: OllamaService,
) {

    private val log = KotlinLogging.logger { }


    @HxRequest
    @GetMapping("/setting")
    fun getSetting(
        model: Model
    ): String {

        val modelSetting =
            ollamaService.getModelSetting()

        model.addAttribute("setting", modelSetting)

        return "component/settingModalForm"
    }

    @PutMapping("/setting")
    @ResponseBody
    fun updateSetting(ollamaRequestRto: OllamaRequestRto) {

        ollamaService.updateChatClient(ollamaRequestRto)
    }


}
