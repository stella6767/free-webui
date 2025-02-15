package freeapp.life.freeaiweb.repo

import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import freeapp.life.freeaiweb.entity.Chat
import freeapp.life.freeaiweb.entity.Message
import freeapp.life.freeaiweb.entity.MessagePair
import freeapp.life.freeaiweb.util.getSingleResultOrNull
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.util.Assert

interface ChatRepository : JpaRepository<Chat, Long>,  ChatCustomRepository {

}

interface ChatCustomRepository {
    fun saveMessage(message: Message): Message
    fun saveMessagePair(messagePair: MessagePair): MessagePair
    fun findMessagePairById(id: Long): MessagePair?
}


class ChatCustomRepositoryImpl(
    private val renderer: JpqlRenderer,
    private val ctx: JpqlRenderContext,
    private val em: EntityManager,
) : ChatCustomRepository {


    override fun saveMessage(message: Message): Message {
        Assert.notNull(message, "Entity must not be null")
        return if (message.id == 0L) {
            em.persist(message)
            message
        } else {
            em.merge(message)
        }
    }


    override fun saveMessagePair(messagePair: MessagePair): MessagePair {
        Assert.notNull(messagePair, "Entity must not be null")
        return if (messagePair.id == 0L) {
            em.persist(messagePair)
            messagePair
        } else {
            em.merge(messagePair)
        }
    }


    override fun findMessagePairById(id: Long): MessagePair? {

        val query = jpql {
            select(
                entity(MessagePair::class),
            ).from(
                entity(MessagePair::class),
                fetchJoin(MessagePair::humanMessage).alias(entity(Message::class, alias = "humanMessage")),
                leftFetchJoin(MessagePair::aiMessage).alias(entity(Message::class, alias = "aiMessage")),
            ).where(
                path(MessagePair::id).equal(id)
            )
        }

        val render = renderer.render(query = query, ctx)
        return em.getSingleResultOrNull(render, MessagePair::class.java)
    }

}
