package freeapp.life.freeaiweb.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*


@Entity
class Message(
    id:Long,
    content:String,
    //isAi:Boolean,
    chat: Chat,
) : BaseEntity(id = id) {

    @Column(nullable = false, length = 100000)
    val content = content

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Chat::class)
    @JoinColumn(name = "chatId")
    val chat = chat

}
