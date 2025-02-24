package freeapp.life.freeaiweb.config

import freeapp.life.freeaiweb.dto.ChatModelHolder
import mu.KotlinLogging
import org.springframework.ai.autoconfigure.ollama.OllamaConnectionProperties
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OllamaConfig(
    private val ollamaConnectionProperties: OllamaConnectionProperties,
) {

    private val log = KotlinLogging.logger { }

    private val defaultOption = OllamaOptions.builder()
        .temperature(0.8) // controls randomness, higher values increase creativity, lower values are more focused
        .topK(40) // limits token pool, higher values increase diversity, lower values are more focused
        .topP(0.9) // affects diversity, higher values increase variety, lower values are more conservative
        .build()

    @Bean
    fun chatModelHolder(): ChatModelHolder {
        val holder = ChatModelHolder(chatModel(), ollamaConnectionProperties.baseUrl)

        return holder
    }

    fun chatModel(): OllamaChatModel {

        val ollamaApi = OllamaApi(ollamaConnectionProperties.baseUrl)

        val listModels = ollamaApi.listModels()
        val smallestModel =
            listModels.models.minByOrNull { it.size }
        defaultOption.model = smallestModel?.model

        val chatModel = OllamaChatModel.builder()
            .ollamaApi(ollamaApi)
            .defaultOptions(defaultOption)
            .build()

        return chatModel
    }


}
