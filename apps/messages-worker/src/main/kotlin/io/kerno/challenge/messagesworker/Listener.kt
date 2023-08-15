package io.kerno.challenge.messagesworker

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.*

@Component
class Listener(
    private val repository: MessageRepository,
    private val template: StringRedisTemplate
) {

    private val log = LoggerFactory.getLogger(Listener::class.java)

    @KafkaListener(topics = ["messages"])
    fun consume(@Header(RECEIVED_KEY) channel: String, @Payload message: String) {
        log.debug("channel = {}, message = {}", channel, message)
        val newMessage = repository.save(Message(UUID.randomUUID(), channel, message))
        template.convertAndSend("messages", newMessage)
    }
}