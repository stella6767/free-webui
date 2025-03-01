package freeapp.life.freeaiweb.config

import freeapp.life.freeaiweb.dto.ChatClientHolder
import mu.KotlinLogging
import org.springframework.ai.autoconfigure.ollama.OllamaConnectionProperties
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor
import org.springframework.ai.chat.memory.InMemoryChatMemory
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OllamaConfig(
    private val ollamaConnectionProperties: OllamaConnectionProperties,
    private val vectorStore: VectorStore
) {

    private val log = KotlinLogging.logger { }

    private val defaultOption = OllamaOptions.builder()
        .temperature(0.8) // controls randomness, higher values increase creativity, lower values are more focused
        .topK(40) // limits token pool, higher values increase diversity, lower values are more focused
        .topP(0.9) // affects diversity, higher values increase variety, lower values are more conservative
        .build()

    //todo tailwindcss cli

    @Bean
    fun chatModelHolder(): ChatClientHolder {

        val ollamaApi = OllamaApi(ollamaConnectionProperties.baseUrl)

        val chatModel = OllamaChatModel.builder()
            .ollamaApi(ollamaApi)
            .defaultOptions(defaultOption)
            .build()

        val searchRequest = SearchRequest.builder()
            .topK(3) // 상위 3개 문서만 검색
            .similarityThreshold(0.7) // 유사도 0.7 이상 문서만 포함
            .build()

        val chatClient = ChatClient.builder(chatModel)
            .defaultAdvisors(
                SimpleLoggerAdvisor(),
                MessageChatMemoryAdvisor(InMemoryChatMemory()),
                QuestionAnswerAdvisor(vectorStore, searchRequest),
            )
            .build()

        val holder =
            ChatClientHolder(ollamaConnectionProperties.baseUrl, chatClient, defaultOption)

        return holder
    }


}
