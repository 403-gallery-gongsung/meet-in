package com.gongsung.common.event

interface EventPublisher<T : Event> {
    fun publish(event: T)
}
