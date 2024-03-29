package com.gongsung.company

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories
@EntityScan
class CompanyRepositoryConfiguration {

    @Bean
    fun companyMemoryRepository(): CompanyMemoryRepository {
        return CompanyMemoryRepository()
    }

    @Bean
    fun companyRepository(companyMemoryRepository: CompanyMemoryRepository): CompanyRepository {
        return CompanyRepository(companyMemoryRepository)
    }
}
