package com.gongsung.user

import com.gongsung.user.persist.CommandPersist
import com.gongsung.user.persist.QueryPersist
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan
class UserServiceConfiguration {

    @Bean
    fun userService(commandPersist: CommandPersist): UserService {
        return UserService(commandPersist)
    }

    @Bean
    fun userLookUpService(queryPersist: QueryPersist): UserLookUpService {
        return UserLookUpService(queryPersist)
    }
}