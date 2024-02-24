package com.gongsung.common.exception

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean

@AutoConfiguration
class ExceptionMessageAutoConfiguration {
    @Bean
    fun commonExceptionMessageRegistrar() =
        ExceptionMessageSourceRegistrar(basename = "com/gongsung/common/exception/message")
}
