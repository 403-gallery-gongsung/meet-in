package com.gongsung.common.event.kafka

import com.gongsung.common.event.Event
import com.gongsung.common.event.EventPublisher
import org.springframework.kafka.core.KafkaTemplate

abstract class KafkaEventPublisher<T : Event>(
    open val topic: String,
    open val template: KafkaTemplate<String, T>
) : EventPublisher<T> {
    abstract fun publish(topic: String, event: T)
    override fun publish(event: T) {
        publish(topic, event)
    }
}
