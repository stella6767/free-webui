package freeapp.life.freeaiweb.dto

import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.Message
import freeapp.life.freeaiweb.entity.MessagePair
import java.time.LocalDateTime


data class AiMessageReqDto(
    val msg: String,
    val clientId: String,
    val chatId: Long = 0,
) {

    fun toEntity(
        content: String
    ): Message {
        return Message(
            content = content,
        )
    }


    fun toChatReqDto(): ChatReqDto {
        val trimMsg = this.msg.trim()
        val name =
            if (trimMsg.length > 5) trimMsg.substring(0, 5) else trimMsg
        return ChatReqDto(
            name = name
        )
    }

}


data class ChatRespDto(
    val id: Long,
    val name: String,
    val messagePairs: MutableList<MessagePair> = mutableListOf(),
    val createdAt: LocalDateTime
) {

    companion object {
        fun fromEntity(
            chat: Chat,
            isNeedMsg:Boolean,
        ): ChatRespDto {

            return ChatRespDto(
                id = chat.id,
                name = chat.name,
                messagePairs = if (isNeedMsg) chat.messagePairs else mutableListOf(),
                createdAt = chat.createdAt
            )
        }

    }

}




data class ChatReqDto(
    var name: String = "",
) {
    fun toEntity(): Chat {
        return Chat(
            name = this.name
        )
    }
}


data class OllamaRequestRto(
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

