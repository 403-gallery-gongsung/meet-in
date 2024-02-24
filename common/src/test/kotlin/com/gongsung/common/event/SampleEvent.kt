package com.gongsung.common.event

import java.time.Instant

class SampleEvent(
    override val timeStamp: Instant,
    override val type: String,
    override val payload: Event.Payload<SamplePayload>
) : Event
