package com.gongsung.common.exception

import org.springframework.http.HttpStatus
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

class ExceptionHttpStatusMapper(
    vararg mapping: Pair<KClass<out MeetInException>, HttpStatus>,
) {
    private val registry = mapping.toList()

    fun convert(
        exception: MeetInException,
    ): HttpStatus {
        return registry
            .firstOrNull { (type, _) -> exception::class.isSubclassOf(type) }
            ?.second
            ?: HttpStatus.INTERNAL_SERVER_ERROR
    }
}
