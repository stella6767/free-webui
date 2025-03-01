package freeapp.life.freeaiweb.service


import freeapp.life.freeaiweb.dto.AIModelDto
import freeapp.life.freeaiweb.dto.ChatClientHolder
import freeapp.life.freeaiweb.dto.OllamaRequestRto
import freeapp.life.freeaiweb.dto.OllamaResponseRto
import freeapp.life.freeaiweb.util.isValidURL

import mu.KotlinLogging
import org.springframework.ai.chat.client.DefaultChatClient
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body


@Service
class OllamaService(
    private val chatClientHolder: ChatClientHolder,
    private val restClient: RestClient,
) {

    private val log = KotlinLogging.logger { }

    fun getModelSetting(): OllamaResponseRto {

        val options =
            chatClientHolder.option

        val ollamaApi = OllamaApi(chatClientHolder.ollamaHost)

        val models = ollamaApi.listModels().models.map {
            AIModelDto(
                model = it.model,
                digest = it.digest,
                name = it.name,
                size = it.size
            )
        }

        return OllamaResponseRto(
            host = chatClientHolder.ollamaHost,
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

        chatClientHolder.updateChatClient(newChatModel, requestRto.host, requestRto.toOllamaOption())
    }


}
