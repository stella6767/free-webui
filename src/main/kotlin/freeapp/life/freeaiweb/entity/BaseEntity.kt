package freeapp.life.freeaiweb.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime


@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class BaseEntity(
    id: Long = 0,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now(),
) : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id
        protected set

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = createdAt
        protected set

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime = updatedAt
        protected set



}
