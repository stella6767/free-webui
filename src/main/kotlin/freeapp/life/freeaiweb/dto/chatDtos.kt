package freeapp.life.freeaiweb.dto

import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.Message


data class AiMessageReqDto(
    val msg: String,
    val clientId:String,
    val chatId:Long,
) {

    fun toEntity(
        content:String
    ): Message {
        return Message(
            content = content,
        )
    }

}


data class ChatReqDto(
    val name:String = "New Chat",
){
    fun toEntity(): Chat {
        return Chat(
            name = this.name
        )
    }
}



data class OllamaRequestRto (
    val model: String = "llama3.1:8b",
    val prompt: String? = null,
    //val messages: List<Message>? = null,
    val isPromptOrMessages: Boolean,
    val temperature: Double = 0.7,
    val stream: Boolean = false,
)


data class OllamaResponseRto(
    val message: String
)

