package com.gongsung.common.event.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.gongsung.common.event.Event
import com.gongsung.common.event.EventConsumer

abstract class KafkaEventConsumer<T : Event>(
    open val objectMapper: ObjectMapper
) : EventConsumer<T>
