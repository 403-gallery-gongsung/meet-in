package com.gongsung.common.exception

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.server.ServerWebInputException
import kotlin.reflect.KClass

@AutoConfiguration
class WebExceptionMapperAutoConfiguration {
    @Bean
    fun exceptionMapper(
        customMappings: List<Pair<KClass<out Throwable>, (Throwable) -> MeetInException>>,
    ): ExceptionMapper {
        return ExceptionMapper(
            *listOf(
                MethodArgumentTypeMismatchException::class,
                HttpMessageNotReadableException::class,
                MethodArgumentNotValidException::class,
                ServerWebInputException::class,
            )
                .map { it to ::FrameworkBadRequestException }
                .toTypedArray(),
            *customMappings.toTypedArray(),
        )
    }

    @Bean
    fun exceptionHttpStatusMapper(
        customMappings: List<Pair<KClass<out MeetInException>, HttpStatus>>,
    ): ExceptionHttpStatusMapper {
        return ExceptionHttpStatusMapper(
            MeetInBadRequestException::class to HttpStatus.BAD_REQUEST,
            MeetInResourceNotFoundException::class to HttpStatus.NOT_FOUND,
            *customMappings.toTypedArray(),
        )
    }
}

internal class FrameworkBadRequestException(
    throwable: Throwable,
) : MeetInBadRequestException(throwable) {
    override val error: MeetInError
        get() = MeetInCommonError.BAD_REQUEST
}
