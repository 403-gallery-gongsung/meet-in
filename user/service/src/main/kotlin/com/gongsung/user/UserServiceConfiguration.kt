package com.gongsung.user

import com.gongsung.user.persist.user.CommandUserPersist
import com.gongsung.user.persist.user.QueryUserPersist
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.gongsung.user")
class UserServiceConfiguration {
    @Bean
    fun userService(commandPersist: CommandUserPersist): UserService {
        return UserService(commandPersist)
    }

    @Bean
    fun userLookUpService(queryPersist: QueryUserPersist): UserLookUpService {
        return UserLookUpService(queryPersist)
    }
}
