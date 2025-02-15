package freeapp.life.freeaiweb.service

import freeapp.life.freeaiweb.dto.ChatReqDto
import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.Message
import freeapp.life.freeaiweb.entity.MessagePair
import freeapp.life.freeaiweb.repo.ChatRepository
import jakarta.persistence.EntityNotFoundException
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


    fun findChats() {

        val chats =
            chatRepository.findAll()

        if (chats.isEmpty()) {

        }

    }


}
