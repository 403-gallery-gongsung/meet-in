package com.gongsung.company.internal

import com.gongsung.company.command.CommandCompanyUseCase
import com.gongsung.company.internal.dto.CompanyRequest
import com.gongsung.company.query.QueryCompanyUseCase
import com.gonsung.company.Company
import com.gonsung.company.CompanyIdentity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/internal/v1/company")
class CompanyInternalAPI(
    @Autowired private val commandUseCase: CommandCompanyUseCase,
    @Autowired private val queryCompanyUseCase: QueryCompanyUseCase
) {

    @GetMapping("/{companyId}")
    fun getCompany(@PathVariable("companyId") companyId: Long): ResponseEntity<Company> {
        return companyId.let(
            CompanyIdentity::of,
        ).run(
            queryCompanyUseCase::getCompanyById,
        ).let {
            ResponseEntity.ok(it)
        }
    }

    @PostMapping
    fun createCompany(@RequestBody companyRequest: CompanyRequest): ResponseEntity<Company> {
        return companyRequest.let(
            commandUseCase::creatCompany,
        ).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/{companyId}")
    fun deleteCompany(@PathVariable("companyId") companyId: Long): ResponseEntity<Boolean> {
        return companyId.let(
            CompanyIdentity::of,
        ).run(
            commandUseCase::deleteCompany,
        ).let {
            ResponseEntity.ok(it)
        }
    }

    @PutMapping("/{companyId}")
    fun updateCompany(
        @PathVariable("companyId") companyId: Long,
        @RequestBody companyRequest: CompanyRequest
    ): ResponseEntity<Company> {
        return Company.of(companyIdentity = CompanyIdentity.of(companyId), companyRequest)
            .let(
                commandUseCase::updateCompany,
            ).let {
                ResponseEntity.ok(it)
            }
    }
}
