package freeapp.life.freeaiweb.service

import AIModelDto
import freeapp.life.freeaiweb.dto.ChatModelHolder
import freeapp.life.freeaiweb.dto.OllamaRequestRto
import freeapp.life.freeaiweb.dto.OllamaResponseRto
import freeapp.life.freeaiweb.util.isValidURL

import mu.KotlinLogging
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body


@Service
class OllamaService(
    private val chatModelHolder: ChatModelHolder,
    private val restClient: RestClient,
) {

    private val log = KotlinLogging.logger { }

    fun getModelSetting(): OllamaResponseRto {

        val chatModel =
            chatModelHolder.getChatModel()

        val options =
            chatModel.defaultOptions

        val ollamaApi = OllamaApi(chatModelHolder.ollamaHost)

        val models = ollamaApi.listModels().models.map {
            AIModelDto(
                model = it.model,
                digest = it.digest,
                name = it.name,
                size = it.size
            )
        }

        return OllamaResponseRto(
            host = chatModelHolder.ollamaHost,
            models,
            currentModel = options.model ?: "",
            temperature = options.temperature ?: 0.0,
            topK = options.topK ?: 0,
            topP = options.topP ?: 0.0
        )
    }


    fun updateChatClient(requestRto: OllamaRequestRto) {

        if (!requestRto.host.isValidURL()){
            throw IllegalArgumentException("Invalid URL")
        }

        try {
            val body =
                restClient.get().uri(requestRto.host + "api/version").retrieve().body<String>()!!
            println(body)
        } catch (e:Exception){
            throw IllegalArgumentException("The Ollama host is not reachable")
        }

        val ollamaApi = OllamaApi(requestRto.host)
        val newChatModel = OllamaChatModel.builder()
            .ollamaApi(ollamaApi)
            .defaultOptions(requestRto.toOllamaOption())
            .build()

        chatModelHolder.updateChatModel(newChatModel, requestRto.host)

        println(chatModelHolder.ollamaHost)
    }


}
