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
    var name = name

    @JsonBackReference
    @OneToMany(mappedBy = "chat", cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY, orphanRemoval = true)
    val messagePairs: MutableList<MessagePair> = mutableListOf()

    fun addMessagePair(messagePair: MessagePair): MessagePair {
        messagePairs.add(messagePair)
        return messagePair
    }

    fun updateName(name: String){
        val rawName = name.trim()
        val finalName =
            if (rawName.length > 20) rawName.substring(0, 20) else rawName
        this.name = finalName
    }


}
