package com.gongsung.common.event

import com.fasterxml.jackson.databind.ObjectMapper
import com.gongsung.common.event.kafka.KafkaEventConsumer
import com.gongsung.common.logger.LazyLogging
import org.springframework.kafka.annotation.KafkaListener

class SampleEventKafkaConsumer(
    override val objectMapper: ObjectMapper
) : KafkaEventConsumer<SampleEvent>(objectMapper), LazyLogging {
    @KafkaListener(topics = ["sample-topic"])
    override fun handle(event: SampleEvent) {
        val eventPayload = objectMapper.treeToValue(event.payload.value, event.payload.payloadType)
        logger.info("Received event: {}, payload: {}", event, eventPayload)
    }
}
