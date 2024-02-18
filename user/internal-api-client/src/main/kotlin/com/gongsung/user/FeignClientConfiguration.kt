package com.gongsung.user

import com.gongsung.user.client.company.CompanyClient
import com.gongsung.user.client.company.CompanyFeignClient
import com.gongsung.user.persist.company.CompanyQueryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.gongsung.user.client"])
open class FeignClientConfiguration {

    @Bean
    open fun companyQueryClient(companyFeignClient: CompanyFeignClient): CompanyQueryClient {
        return CompanyClient(companyFeignClient)
    }
}
