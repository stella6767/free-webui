package freeapp.life.freeaiweb.service

import freeapp.life.freeaiweb.dto.OllamaRequestRto
import freeapp.life.freeaiweb.dto.OllamaResponseRto
import mu.KotlinLogging
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient


@Service
class OllamaService(
    private val ollamaChatModel: OllamaChatModel,
    private val ollamaClient: RestClient,
    private val builder: ChatClient.Builder,
    private val chatClient: ChatClient,
) {

    private val log = KotlinLogging.logger {  }
    private val ollamaApi = OllamaApi()
    //private val chatClient = builder.build()


    fun updateChatClient(requestRto: OllamaRequestRto) {

        val ollamaApi = OllamaApi(requestRto.host)
        val newClient =
            chatClient.mutate().defaultOptions(requestRto.toOllamaOption()).build()



        val listModels = ollamaApi.listModels()

        println(newClient)
        println(chatClient)
    }


    fun generateLlmResponse(requestRto: OllamaRequestRto): OllamaResponseRto? {
        log.info("Request: {}", requestRto)
//        if (requestRto.prompt != null) {
//            return generateBasedOnPrompt(requestRto)
//        }
//        else if (requestRto.getMessages() != null) {
//            return generateBasedOnMessages(requestRto)
//        }
        return null
    }





    private fun generateBasedOnPrompt(requestRto: OllamaRequestRto): OllamaResponseRto {

        val options = OllamaOptions.builder()
            .model("")
            .temperature(1.0)
            .topK(1)
            .topP(2.0)
            .build()

        val client2 = builder.defaultOptions(options).build()
        //client2.mutate().defaultOptions(options).build()

        val chatModel = OllamaChatModel.builder()
            .ollamaApi(ollamaApi)
            .defaultOptions(options)
            .build()

//        val call = chatModel.call("requestRto.prompt")
//        val stream = chatModel.stream("")

        //val ollamaResponseRto = OllamaResponseRto()

//        ollamaResponseRto.setMessage(call)
//        return ollamaResponseRto

        TODO()

    }

//    private fun generateBasedOnMessages(requestRto: OllamaRequestRto): OllamaResponseRto {
//        val chatModel: ChatModel = OllamaChatModel(
//            ollamaApi,
//            OllamaOptions.create()
//                .withModel(requestRto.model)
//                .withTemperature(requestRto.temperature)
//        )
//        val ollamaResponseRto = OllamaResponseRto()
//        ollamaResponseRto.setMessage(chatModel.call(requestRto.getMessages() as Message?))
//        return ollamaResponseRto
//    }

}
