package com.gongsung.auth

import com.querydsl.jpa.impl.JPAQueryFactory
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
    private val jpaQueryFactory: JPAQueryFactory
) {
    @Bean
    fun userRepository(): UserRepository {
        return UserRepository(entityManager, jpaQueryFactory)
    }

    @Bean
    fun companyRepository(): CompanyRepository {
        return CompanyRepository(entityManager, jpaQueryFactory)
    }
    @Bean
    fun authRepository(): AuthRepository {
        return AuthRepository(jpaQueryFactory)
    }
}