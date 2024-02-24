package com.gongsung.auth

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan
class RepositoryConfiguration {
    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Bean
    fun userRepository(): UserRepository {
        return UserRepository(entityManager)
    }

    @Bean
    fun companyRepository(): CompanyRepository {
        return CompanyRepository(entityManager)
    }
}