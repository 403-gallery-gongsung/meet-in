package com.gongsung.auth

import com.gongsung.auth.authority.JwtTokenProvider
import com.gongsung.auth.lookup.CompanyLookUpService
import com.gongsung.auth.lookup.UserLookUpService
import com.gongsung.auth.persist.command.CommandCompanyPersist
import com.gongsung.auth.persist.command.CommandUserPersist
import com.gongsung.auth.persist.query.QueryCompanyPersist
import com.gongsung.auth.persist.query.QueryUserPersist
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

@Configuration
@ComponentScan
class ServiceConfiguration {
    @Bean
    fun authService(
        authenticationManagerBuilder: AuthenticationManagerBuilder,
        jwtTokenProvider: JwtTokenProvider
    ): AuthService {
        return AuthService(authenticationManagerBuilder, jwtTokenProvider)
    }

    @Bean
    fun userLookUpService(
        queryUserPersist: QueryUserPersist
    ): UserLookUpService {
        return UserLookUpService(queryUserPersist)
    }

    @Bean
    fun userService(
        commandUserPersist: CommandUserPersist
    ): UserService {
        return UserService(commandUserPersist)
    }

    @Bean
    fun companyLookUpService(
        queryCompanyPersist: QueryCompanyPersist
    ): CompanyLookUpService {
        return CompanyLookUpService(queryCompanyPersist)
    }

    @Bean
    fun companyService(
        commandCompanyPersist: CommandCompanyPersist
    ): CompanyService {
        return CompanyService(commandCompanyPersist)
    }
}