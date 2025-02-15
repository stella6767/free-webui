package freeapp.life.freeaiweb.entity


import jakarta.persistence.*


@Entity
class Message(
    id:Long = 0,
    content:String,
) : BaseEntity(id = id) {

    @Column(nullable = false, length = 100000)
    val content = content

}
