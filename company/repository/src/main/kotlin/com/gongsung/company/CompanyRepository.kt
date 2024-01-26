package com.gongsung.company

import com.gongsung.company.persist.CommandCompanyPersist
import com.gongsung.company.persist.QueryCompanyPersist
import com.gonsung.company.Company
import com.gonsung.company.CompanyProps


class CompanyRepository(
    private val companyMemoryRepository: CompanyMemoryRepository
) : CommandCompanyPersist, QueryCompanyPersist {
    override fun createCompany(companyProps: CompanyProps): Company {
        return companyMemoryRepository.createCompany(companyProps)
    }

    override fun deleteCompanyById(id: Long): Boolean {
        return companyMemoryRepository.deleteCompanyById(id)
    }

    override fun updateCompany(company: Company): Company {
        return companyMemoryRepository.updateCompany(company)
    }

    override fun getCompanyById(id: Long): Company {
        return companyMemoryRepository.getCompanyById(id)
    }
}
