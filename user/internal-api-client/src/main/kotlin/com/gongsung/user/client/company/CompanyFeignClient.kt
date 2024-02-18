package com.gongsung.user.client.company

import com.gongsung.user.client.company.dto.CompanyResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(name = "company", url = "http://localhost:8081/api/internal/v1/company")
interface CompanyFeignClient {
    @GetMapping("/{companyId}")
    fun getCompanyById(@PathVariable("companyId") companyId: Long): CompanyResponse
}
