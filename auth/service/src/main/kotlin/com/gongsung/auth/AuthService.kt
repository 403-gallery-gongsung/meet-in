package com.gongsung.auth

import com.gongsung.auth.command.CommandAuthUseCase
import com.gongsung.auth.persist.CommandAuthPersist
import com.gongsung.auth.authority.JwtTokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val commandAuthPersist: CommandAuthPersist,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) : CommandAuthUseCase {

    override fun signIn(accountProps: AccountProps): TokenInfo {
//        Todo: get Account Information status for calling module such as AccountType via Feign

        val authenticationToken = UsernamePasswordAuthenticationToken(accountProps.loginId, accountProps.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}