package com.gongsung.auth

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan
class RepositoryConfiguration(
    @PersistenceContext
    private val entityManager: EntityManager,
) {
    @Bean
    fun authRepository(): AuthRepository {
        return AuthRepository(entityManager)
    }
}
