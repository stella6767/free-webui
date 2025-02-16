package freeapp.life.freeaiweb.service

import freeapp.life.freeaiweb.dto.ChatReqDto
import freeapp.life.freeaiweb.dto.ChatRespDto
import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.Message
import freeapp.life.freeaiweb.entity.MessagePair
import freeapp.life.freeaiweb.repo.ChatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ChatService(
    private val chatRepository: ChatRepository
) {

    @Transactional
    fun saveChat(chatReqDto: ChatReqDto): Chat {
        return chatRepository.save(chatReqDto.toEntity())
    }

    @Transactional
    fun saveMessage(message: Message): Message {
        return chatRepository.saveMessage(message)
    }

    @Transactional
    fun saveMessagePair(messagePair: MessagePair): MessagePair {
        return chatRepository.saveMessagePair(messagePair)
    }

    @Transactional
    fun updateMessagePair(id:Long, aiMessage: Message){
        val messagePair =
            chatRepository.findMessagePairById(id) ?: throw EntityNotFoundException()

        messagePair.aiMessage = aiMessage
    }


    @Transactional(readOnly = true)
    fun findChatById(id: Long): Chat {
        val chat =
            chatRepository.findByIdOrNull(id) ?: throw EntityNotFoundException()
        return chat
    }


    @Transactional(readOnly = true)
    fun findChatRespById(chatId:Long): ChatRespDto {
        val chat = findChatById(chatId)
        return ChatRespDto.fromEntity(chat, true)
    }


    @Transactional(readOnly = true)
    fun findChatsByPage(pageable: Pageable): Page<ChatRespDto> {
        return chatRepository.findChatsByPage(pageable).map {
            ChatRespDto.fromEntity(it, false)
        }
    }



}
