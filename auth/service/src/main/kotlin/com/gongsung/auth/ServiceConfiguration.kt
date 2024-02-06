package com.gongsung.auth

import com.gongsung.auth.persist.CommandPersist
import com.gongsung.auth.persist.QueryPersist
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    QueryPersist::class, CommandPersist::class
)
class ServiceConfiguration {

    @Bean
    fun AuthService(
        queryPersist: QueryPersist,
        commandPersist: CommandPersist
    ): AuthService {
        return AuthService(queryPersist, commandPersist)
    }
}