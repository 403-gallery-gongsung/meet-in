package com.gongsung.company.query

import com.gonsung.company.Company
import com.gonsung.company.CompanyIdentity

interface QueryCompanyUseCase {
    fun getCompanyById(id: CompanyIdentity): Company
}
