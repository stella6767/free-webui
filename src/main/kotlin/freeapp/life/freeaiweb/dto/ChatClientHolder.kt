package freeapp.life.freeaiweb.dto

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaOptions
import java.util.concurrent.atomic.AtomicReference

class ChatClientHolder(
    host:String,
    initialChatClient: ChatClient,
    var option: OllamaOptions,
) {

    var ollamaHost: String = host

    private val chatClientRef =
        AtomicReference(initialChatClient)

    fun getChatClient(): ChatClient = chatClientRef.get()

    fun updateChatClient(newChatModel: OllamaChatModel,
                         host: String,
                         newOllamaOption: OllamaOptions) {
        val chatClient =
            ChatClient.builder(newChatModel).build()
        chatClientRef.set(chatClient)
        option = newOllamaOption
        ollamaHost = host
    }

}
