package com.gongsung.port

import com.gonsung.company.Company
import com.gonsung.company.CompanyProps

interface CommandCompanyPort {
    fun creatCompany(companyProps: CompanyProps): Company
    fun deleteCompanyById(id: Long): Boolean
}
