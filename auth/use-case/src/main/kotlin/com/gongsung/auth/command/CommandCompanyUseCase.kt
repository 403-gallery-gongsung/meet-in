package com.gongsung.auth.command

import com.gongsung.auth.Company
import com.gongsung.auth.CompanyProps

interface CommandCompanyUseCase {
    fun createCompany(props: CompanyProps): Company
}
