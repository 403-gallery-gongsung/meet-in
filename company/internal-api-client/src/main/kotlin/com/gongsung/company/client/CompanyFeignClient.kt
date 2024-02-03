package com.gongsung.company.client

import com.gongsung.company.command.CommandCompanyUseCase
import com.gongsung.company.internal.dto.CompanyRequest
import com.gongsung.company.query.QueryCompanyUseCase
import com.gonsung.company.Company
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping

@FeignClient(name = "company", url = "\${company.url}/internal/v1/company}")
interface CompanyFeignClient : CommandCompanyUseCase, QueryCompanyUseCase {

    @PostMapping
    fun creatCompany(companyRequest: CompanyRequest): ResponseEntity<Company>

    @GetMapping("/{companyId}")
    fun getCompany(@PathVariable("companyId") companyId: Long): ResponseEntity<Company>

    @DeleteMapping("/{companyId}")
    fun deleteCompany(@PathVariable("companyId") companyId: Long): ResponseEntity<Boolean>

    @PutMapping("/{companyId}")
    fun updateCompany(
        @PathVariable("companyId") companyId: Long,
        companyRequest: CompanyRequest
    ): ResponseEntity<Company>

}
