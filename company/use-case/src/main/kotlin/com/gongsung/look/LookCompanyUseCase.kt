package com.gongsung.look

import com.gonsung.company.Company
import com.gonsung.company.CompanyIdentity

interface LookCompanyUseCase {
    fun getCompanyById(id: CompanyIdentity): Company
}
