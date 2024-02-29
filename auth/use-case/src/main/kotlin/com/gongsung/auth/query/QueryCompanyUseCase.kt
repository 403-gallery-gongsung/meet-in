package com.gongsung.auth.query

import com.gongsung.auth.Company
import com.gongsung.auth.CompanyIdentity

interface QueryCompanyUseCase {
    fun getCompanyById(id: CompanyIdentity): Company
}
