package com.gongsung.user.client.company

import com.gongsung.company.Company
import com.gongsung.user.persist.company.CompanyQueryClient

class CompanyClient(
    private val companyFeignClient: CompanyFeignClient
) : CompanyQueryClient {
    override fun getCompanyById(companyId: Long): Result<Company> {
        return runCatching {
            companyFeignClient.getCompanyById(companyId)
        }
    }
}
