package com.gongsung.command

import com.gonsung.company.Company
import com.gonsung.company.CompanyIdentity

interface CommandCompanyUseCase {
    fun creatCompany(companyDTO: Company): Company
    fun deleteCompany(id: CompanyIdentity): Boolean
    fun updateCompany(companyDTO: Company): Company
}
