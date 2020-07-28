package com.fleet.speedproducer.kafka.controller

import com.fleet.speedproducer.kafka.producer.MessageProducer
import com.fleet.speedproducer.model.SpeedMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SpeedReceiverController @Autowired constructor(private val messageProducer: MessageProducer) {

    @Async
    @PostMapping("/speed-receiver")
    fun receiveSpeedData(@RequestBody speedMessage: SpeedMessage) {
        messageProducer.sendMessage(speedMessage)
    }
}