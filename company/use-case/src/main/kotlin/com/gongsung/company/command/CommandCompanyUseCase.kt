package com.gongsung.company.command

import com.gonsung.company.Company
import com.gonsung.company.CompanyIdentity
import com.gonsung.company.CompanyProps

interface CommandCompanyUseCase {
    fun creatCompany(companyProps: CompanyProps): Company
    fun deleteCompany(id: CompanyIdentity): Boolean
    fun updateCompany(company: Company): Company
}
