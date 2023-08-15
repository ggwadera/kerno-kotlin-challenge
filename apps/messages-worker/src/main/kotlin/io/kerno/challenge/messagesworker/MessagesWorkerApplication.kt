package io.kerno.challenge.messagesworker

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@SpringBootApplication
class MessagesWorkerApplication {
	@Bean
	fun admin() = KafkaAdmin(mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092"))

	@Bean
	fun topic1() = TopicBuilder.name("messages").build()

	@Bean
	fun stringTemplate(rcf: RedisConnectionFactory, om: ObjectMapper) = StringRedisTemplate(rcf).apply {
		valueSerializer = GenericJackson2JsonRedisSerializer(om)
	}

}

fun main(args: Array<String>) {
	runApplication<MessagesWorkerApplication>(*args)
}
