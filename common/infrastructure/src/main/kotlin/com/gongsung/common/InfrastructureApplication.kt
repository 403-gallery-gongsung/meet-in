package com.gongsung.common

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class InfrastructureApplication

fun main(args: Array<String>) {
    runApplication<InfrastructureApplication>(*args)
}