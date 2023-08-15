package io.kerno.challenge.messagesworker

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "message")
class Message(
    @Id
    val id: UUID,

    val channel: String,

    @Column(columnDefinition = "text")
    val message: String
) : Serializable {
}