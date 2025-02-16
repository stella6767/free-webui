package freeapp.life.freeaiweb.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class Chat(
    id: Long = 0,
    name:String,
) : BaseEntity(id = id) {

    @Column(nullable = false, length = 300)
    val name = name


    @JsonBackReference
    @OneToMany(mappedBy = "chat", cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY, orphanRemoval = true)
    val messagePairs: MutableList<MessagePair> = mutableListOf()
    var deletedAt: LocalDateTime? = null
        protected set

    fun addMessagePair(messagePair: MessagePair): MessagePair {
        messagePairs.add(messagePair)
        return messagePair
    }

}
