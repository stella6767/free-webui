package freeapp.life.freeaiweb.dto

import AIModelDto
import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.Message
import freeapp.life.freeaiweb.entity.MessagePair
import org.springframework.ai.ollama.api.OllamaOptions
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
            if (trimMsg.length > 20) trimMsg.substring(0, 20) else trimMsg
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

            // 필요한 경우 lazy 로딩된 컬렉션을 명시적으로 초기화하여 MutableList로 변환
            val messagePairs =
                if (isNeedMsg) chat.messagePairs.toMutableList() else mutableListOf()

            return ChatRespDto(
                id = chat.id,
                name = chat.name,
                messagePairs = messagePairs,
                createdAt = chat.createdAt
            )
        }

    }

}




data class ChatReqDto(
    var name: String = "",
) {
    fun toEntity(id: Long= 0): Chat {
        val rawName = this.name.trim()
        val name =
            if (rawName.length > 20) rawName.substring(0, 20) else rawName
        return Chat(
            id = id,
            name = name
        )
    }
}


data class OllamaRequestRto(
    val host :String,
    val model: String,
    val temperature: Double,
    val topK : Int,
    val topP:Double,
) {

    fun toOllamaOption(): OllamaOptions {

        val options = OllamaOptions.builder()
            .model(this.model)
            .temperature(this.temperature)
            .topK(this.topK)
            .topP(this.topP)
            .build()

        return options
    }

}


data class OllamaResponseRto(
    val host:String,
    val models: List<AIModelDto>,
    val currentModel:String,
    val temperature: Double,
    val topK: Int,
    val topP:Double,
)

