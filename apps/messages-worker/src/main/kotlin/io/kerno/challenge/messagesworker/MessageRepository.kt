package io.kerno.challenge.messagesworker

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MessageRepository : JpaRepository<Message, UUID> {
}