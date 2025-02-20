package freeapp.life.freeaiweb.entity


import jakarta.persistence.Column
import jakarta.persistence.Entity


@Entity
class Message(
    id:Long = 0,
    content:String,
) : BaseEntity(id = id) {

    @Column(nullable = false, length = 100000)
    var content = content

}
