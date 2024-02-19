package com.gongsung.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan
class UserApplication

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}