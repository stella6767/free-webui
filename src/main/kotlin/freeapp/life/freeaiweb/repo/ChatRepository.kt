package freeapp.life.freeaiweb.repo

import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.Message
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.util.Assert

interface ChatRepository : JpaRepository<Chat, Long>,  ChatCustomRepository {

}


interface ChatCustomRepository {

}


class ChatCustomRepositoryImpl(
    private val renderer: JpqlRenderer,
    private val ctx: JpqlRenderContext,
    private val em: EntityManager,
) : ChatCustomRepository {


    fun saveMessage(message: Message): Message {
        Assert.notNull(message, "Entity must not be null")
        return if (message.id == 0L) {
            em.persist(message)
            message
        } else {
            em.merge(message)
        }
    }


}
