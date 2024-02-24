package com.gongsung.auth.lookup

import com.gongsung.auth.Company
import com.gongsung.auth.CompanyIdentity
import com.gongsung.auth.persist.query.QueryCompanyPersist
import com.gongsung.auth.query.QueryCompanyUseCase

class CompanyLookUpService(
    private val queryCompanyPersist: QueryCompanyPersist
):QueryCompanyUseCase {
    override fun getCompanyById(id: CompanyIdentity): Company {
        return queryCompanyPersist.getCompanyById(id.companyIdentity)
    }
}