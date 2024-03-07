package com.gongsung.common.exception

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Locale

@RestControllerAdvice
class ServletWebExceptionHandler(
    protected val exceptionMapper: ExceptionMapper,
    protected val exceptionHttpStatusMapper: ExceptionHttpStatusMapper,
    private val messageSource: MessageSource,
) : ResponseEntityExceptionHandler() {
    private val log = KotlinLogging.logger { }

    @ExceptionHandler(MeetInException::class)
    fun handleMeetInException(
        meetInException: MeetInException,
        webRequest: WebRequest,
    ): ResponseEntity<Any>? =
        handleMeetInException(
            meetInException = meetInException,
            body = null,
            httpHeaders = HttpHeaders(),
            httpStatusCode = exceptionHttpStatusMapper.convert(meetInException),
            webRequest = webRequest,
        )

    @ExceptionHandler(Throwable::class)
    fun handleOtherException(
        throwable: Throwable,
        webRequest: WebRequest,
    ): ResponseEntity<Any>? {
        val exception = exceptionMapper.convert(throwable)

        return handleMeetInException(exception, webRequest)
    }

    protected fun handleMeetInException(
        meetInException: MeetInException,
        body: Any?,
        httpHeaders: HttpHeaders,
        httpStatusCode: HttpStatusCode,
        webRequest: WebRequest,
    ): ResponseEntity<Any>? {
        loggingException(meetInException)

        if (webRequest is ServletWebRequest) {
            webRequest.response?.run {
                if (isCommitted) {
                    if (logger.isWarnEnabled) {
                        logger.warn { "Response already committed. Ignoring: $meetInException" }
                    }
                }
            }
        }

        val locale = LocaleContextHolder.getLocale()

        var responseBody = body
        if (body == null && meetInException is ErrorResponse) {
            responseBody = meetInException.updateAndGetBody(messageSource, locale)
        }

        if (responseBody == null) {
            responseBody = createProblemDetail(
                meetInException,
                httpStatusCode,
                "Error",
                meetInException.error.messageKey,
                meetInException.getMessageArguments(),
                webRequest,
            )
        }

        if (responseBody is ProblemDetail) {
            responseBody.setProperty("code", meetInException.error.code)
            responseBody.setProperty("message", getMessage(meetInException))
        }

        return createResponseEntity(responseBody, httpHeaders, httpStatusCode, webRequest)
    }

    private fun loggingException(
        exception: MeetInException,
    ) {
        when (exception.error.level) {
            MeetInErrorLevel.INFO -> log.info(exception) { exception.message }
            MeetInErrorLevel.WARNING -> log.warn(exception) { exception.message }
            MeetInErrorLevel.ERROR -> log.error(exception) { exception.message }
        }
    }

    private fun getMessage(
        meetInException: MeetInException,
    ) {
        messageSource.getMessage(
            meetInException.error.messageKey,
            null,
            Locale.KOREAN,
        )
    }
}
