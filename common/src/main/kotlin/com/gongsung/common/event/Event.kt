package com.gongsung.common.event

import com.fasterxml.jackson.databind.JsonNode
import java.time.Instant

interface Event {
    val type: String
    val payload: Payload<*>
    val timeStamp: Instant

    class Payload<T>(
        val value: JsonNode,
        val payloadType: Class<T>
    )
}
