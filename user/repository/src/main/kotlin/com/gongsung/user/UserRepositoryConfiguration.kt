package com.gongsung.user

import com.gongsung.user.UserRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan
class UserRepositoryConfiguration {
    @Bean
    fun userRepository(): UserRepository {
        return UserRepository()
    }
}