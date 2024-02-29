package com.gongsung.common.exception

import kotlin.reflect.KClass

class ExceptionMapper(
    vararg mapping: Pair<KClass<out Throwable>, (Throwable) -> MeetInException>,
) {
    private val mappingRegistry = mapping.toMap()

    fun convert(
        throwable: Throwable,
    ): MeetInException {
        return mappingRegistry[throwable::class]
            ?.invoke(throwable)
            ?: findMeetInException(throwable)
            ?: MeetInUnknownException(throwable)
    }

    private fun findMeetInException(
        throwable: Throwable,
    ): MeetInException? {
        return if (throwable is MeetInException) {
            throwable
        } else {
            throwable.cause
                ?.let(::findMeetInException)
        }
    }
}
