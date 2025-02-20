package freeapp.life.freeaiweb.dto

import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.ollama.OllamaChatModel
import java.util.concurrent.atomic.AtomicReference

class ChatModelHolder(
    initialChatModel: ChatModel
) {
    private val chatModelRef =
        AtomicReference(initialChatModel)

    fun getChatModel(): ChatModel = chatModelRef.get()

    fun updateChatModel(newChatModel: OllamaChatModel) {
        chatModelRef.set(newChatModel)
    }

}
