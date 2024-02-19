package com.gongsung.auth

import com.gongsung.auth.command.CommandUseCase
import com.gongsung.auth.persist.CommandPersist
import com.gongsung.auth.persist.QueryPersist
import com.gongsung.auth.query.QueryUseCase
import com.gongsung.auth.security.authority.JwtTokenProvider
import com.gongsung.auth.security.authority.TokenInfo
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

class AuthService(
    private val queryPersist: QueryPersist,
    private val commandPersist: CommandPersist,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) : QueryUseCase, CommandUseCase {
    override fun signIn(accountProps: AccountProps): TokenInfo {
//        Todo: get Account Information status for calling module such as AccountType via Feign

        val authenticationToken = UsernamePasswordAuthenticationToken(accountProps.loginId, accountProps.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }

    override fun getAccountById(id: AccountIdentity): Account {
        return queryPersist.getAccountById(id.accountIdentity)
    }

}