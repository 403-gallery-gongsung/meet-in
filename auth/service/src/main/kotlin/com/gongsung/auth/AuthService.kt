package com.gongsung.auth

import com.gongsung.auth.command.CommandAuthUseCase
import com.gongsung.auth.authority.JwtTokenProvider
import com.gongsung.auth.command.CommandCompanyUseCase
import com.gongsung.auth.command.CommandUserUseCase
import com.gongsung.auth.persist.command.CommandCompanyPersist
import com.gongsung.auth.persist.command.CommandUserPersist
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) : CommandAuthUseCase{
    override fun signIn(props: AccountProps): TokenInfo {
//        Todo:
        val authenticationToken = UsernamePasswordAuthenticationToken(props.loginId, props.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}