package com.gongsung.auth

import com.gongsung.auth.authority.JwtTokenProvider
import com.gongsung.auth.persist.CommandAuthPersist
import com.gongsung.auth.persist.QueryAuthPersist
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

@Configuration
@ComponentScan
class ServiceConfiguration {
    @Bean
    fun authService(
        commandAuthPersist: CommandAuthPersist,
        authenticationManagerBuilder: AuthenticationManagerBuilder,
        jwtTokenProvider: JwtTokenProvider
    ): AuthService {
        return AuthService(commandAuthPersist, authenticationManagerBuilder, jwtTokenProvider)
    }

    @Bean
    fun authLookUpService(
        queryAuthPersist: QueryAuthPersist
    ): AuthLookUpService {
        return AuthLookUpService(queryAuthPersist)
    }
}