package com.gongsung.common.event

import com.gongsung.common.event.kafka.KafkaEventPublisher
import com.gongsung.common.logger.LazyLogging
import org.springframework.kafka.core.KafkaTemplate

class SampleEventKafkaPublisher(
    override val topic: String,
    override val template: KafkaTemplate<String, SampleEvent>
) : KafkaEventPublisher<SampleEvent>(topic, template), LazyLogging {
    override fun publish(topic: String, event: SampleEvent) {
        template.send(topic, event)
            .whenCompleteAsync { _, e ->
                if (e != null) {
                    logger.error("Failed to publish event: $event", e)
                }
                logger.debug("Published event: {}", event)
            }
    }
}
