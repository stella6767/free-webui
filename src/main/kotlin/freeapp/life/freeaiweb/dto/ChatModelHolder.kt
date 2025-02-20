package freeapp.life.freeaiweb.dto

import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.ollama.OllamaChatModel
import java.util.concurrent.atomic.AtomicReference

class ChatModelHolder(
    initialChatModel: ChatModel,
    host:String,
) {

    var ollamaHost: String = host

    private val chatModelRef =
        AtomicReference(initialChatModel)

    fun getChatModel(): ChatModel = chatModelRef.get()

    fun updateChatModel(newChatModel: OllamaChatModel, host: String) {
        chatModelRef.set(newChatModel)
        ollamaHost = host
    }

}
