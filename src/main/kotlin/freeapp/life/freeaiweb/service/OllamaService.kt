package freeapp.life.freeaiweb.service

import AIModelDto
import freeapp.life.freeaiweb.dto.ChatModelHolder
import freeapp.life.freeaiweb.dto.OllamaRequestRto
import freeapp.life.freeaiweb.dto.OllamaResponseRto
import freeapp.life.freeaiweb.util.ollamaHost
import mu.KotlinLogging
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap


@Service
class OllamaService(
    private val chatModelHolder: ChatModelHolder,
) {

    private val log = KotlinLogging.logger { }

    fun getModelSetting(): OllamaResponseRto {

        val chatModel =
            chatModelHolder.getChatModel()

        val options =
            chatModel.defaultOptions

        val ollamaApi = OllamaApi(ollamaHost)

        val models = ollamaApi.listModels().models.map {
            AIModelDto(
                model = it.model,
                digest = it.digest,
                name = it.name,
                size = it.size
            )
        }

        return OllamaResponseRto(
            host = ollamaHost,
            models,
            currentModel = options.model ?: "",
            temperature = options.temperature ?: 0.0,
            topK = options.topK ?: 0,
            topP = options.topP ?: 0.0
        )
    }


    fun updateChatClient(requestRto: OllamaRequestRto) {

        val ollamaApi = OllamaApi(requestRto.host)
        ollamaHost = requestRto.host

        val newChatModel = OllamaChatModel.builder()
            .ollamaApi(ollamaApi)
            .defaultOptions(requestRto.toOllamaOption())
            .build()

        chatModelHolder.updateChatModel(newChatModel)
    }


}
