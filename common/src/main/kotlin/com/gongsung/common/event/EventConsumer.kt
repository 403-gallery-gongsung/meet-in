package com.gongsung.common.event

interface EventConsumer<T : Event> {
    fun handle(event: T)
}
