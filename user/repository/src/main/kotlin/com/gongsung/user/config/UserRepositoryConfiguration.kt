
package com.gongsung.user.config

import com.gongsung.user.UserRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan
class UserRepositoryConfiguration {
    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Bean
    fun userRepository(): UserRepository {
        return UserRepository(entityManager)
    }
}
