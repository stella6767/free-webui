package freeapp.life.freeaiweb.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
class MessagePair(
    id: Long = 0,
    humanMessage: Message,
    aiMessage: Message?,
    chat: Chat,
) : BaseEntity(id = id) {

    @OneToOne(optional = false, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "humanMessageId", nullable = false)
    val humanMessage = humanMessage

    @OneToOne(optional = true, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "aiMessageId")
    var aiMessage = aiMessage

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Chat::class)
    @JoinColumn(name = "chatId")
    val chat = chat

}
