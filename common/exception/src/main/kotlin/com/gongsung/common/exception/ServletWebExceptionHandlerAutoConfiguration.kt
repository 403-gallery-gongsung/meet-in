package com.gongsung.common.exception

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Import

@Import(ServletWebExceptionHandler::class)
@AutoConfiguration
class ServletWebExceptionHandlerAutoConfiguration
