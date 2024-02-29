package com.gongsung.auth.persist.command

import com.gongsung.auth.Company
import com.gongsung.auth.CompanyProps

interface CommandCompanyPersist {
    fun createCompany(company: CompanyProps): Company
}
