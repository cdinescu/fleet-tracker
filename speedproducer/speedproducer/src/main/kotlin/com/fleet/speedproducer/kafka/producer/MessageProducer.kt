package com.fleet.speedproducer.kafka.producer

import com.fleet.speedproducer.model.SpeedMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.ListenableFuture


class MessageProducer constructor(private val kafkaTemplate: KafkaTemplate<String, SpeedMessage>,
                                  private val topicName: String) {

    fun sendMessage(speedMessage: SpeedMessage): ListenableFuture<SendResult<String, SpeedMessage>> {
        return kafkaTemplate.send(topicName, speedMessage)
    }
}