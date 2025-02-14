package freeapp.life.freeaiweb.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*


@Entity
class Chat(
    id: Long,
    model: String,
    name:String,
    host:String,
    temperature:Double,
    topP: Double,
    topK: Double,
) : BaseEntity(id = id) {

    @Column(nullable = false, length = 300)
    val name = name

    @Column(nullable = false, length = 300)
    val model = model

    @Column(nullable = false, length = 300)
    val host = host

    val temperature = temperature

    val topP = topP

    val topK = topK

    @JsonBackReference
    @OneToMany(mappedBy = "chat", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val messages: MutableList<Message> = mutableListOf()

}
