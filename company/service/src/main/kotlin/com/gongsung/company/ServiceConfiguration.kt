package com.gongsung.company

import com.gongsung.company.persist.CommandCompanyPersist
import com.gongsung.company.persist.QueryCompanyPersist
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    QueryCompanyPersist::class, CommandCompanyPersist::class,
)
class ServiceConfiguration {

    @Bean
    fun companyService(
        queryCompanyPersist: QueryCompanyPersist,
        commandCompanyPersist: CommandCompanyPersist
    ): CompanyService {
        return CompanyService(queryCompanyPersist, commandCompanyPersist)
    }
}
