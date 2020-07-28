package com.fleet.speedproducer.kafka.producer

import com.fleet.speedproducer.model.SpeedMessage
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@Configuration
class KafkaProducerConfig(@Value(value = "\${kafka.bootstrapAddress}")
                          private val bootstrapAddress: String,
                          @Value(value = "\${speed.topic.name}")
                          private val topicName: String) {

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, SpeedMessage> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, SpeedMessage> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun messageProducer(): MessageProducer {
        return MessageProducer(kafkaTemplate(), topicName)
    }
}