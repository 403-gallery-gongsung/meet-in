package com.gongsung.auth

import com.gongsung.auth.authority.JwtTokenProvider
import com.gongsung.auth.persist.query.QueryAuthPersist
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@ComponentScan
class ServiceConfiguration {
    @Bean
    fun signInService(
        authenticationManagerBuilder: AuthenticationManagerBuilder,
        jwtTokenProvider: JwtTokenProvider,
    ): SignInService {
        return SignInService(authenticationManagerBuilder, jwtTokenProvider)
    }

    @Bean
    fun customUserDetailsService(
        queryAuthPersist: QueryAuthPersist,
        passwordEncoder: PasswordEncoder,
    ): CustomUserDetailsService {
        return CustomUserDetailsService(queryAuthPersist, passwordEncoder)
    }
}
