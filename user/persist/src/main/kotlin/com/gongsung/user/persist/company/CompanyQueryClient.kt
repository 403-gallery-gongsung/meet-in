package com.gongsung.user.persist.company

import com.gongsung.company.Company

interface CompanyQueryClient {
    fun getCompanyById(companyId: Long): Result<Company>
}
