package com.gongsung.company.persist

import com.gonsung.company.Company
import com.gonsung.company.CompanyProps

interface CommandCompanyPersist {
    fun createCompany(companyProps: CompanyProps): Company
    fun deleteCompanyById(id: Long): Boolean
    fun updateCompany(company: Company): Company
}
