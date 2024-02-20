package com.gongsung.auth

import com.gongsung.auth.command.CommandAuthUseCase
import com.gongsung.auth.dto.AuthRequest
import com.gongsung.auth.query.QueryAuthUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthApi(
    private val queryUseCase: QueryAuthUseCase,
    private val commandUseCase: CommandAuthUseCase
) {
    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid authRequest: AuthRequest): ResponseEntity<TokenInfo> {
        return authRequest.let(commandUseCase::signIn)
            .let { ResponseEntity.ok(it) }
    }
}