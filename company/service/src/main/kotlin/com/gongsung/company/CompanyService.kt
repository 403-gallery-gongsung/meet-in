package com.gongsung.company

import com.gongsung.company.command.CommandCompanyUseCase
import com.gongsung.company.persist.CommandCompanyPersist
import com.gongsung.company.persist.QueryCompanyPersist
import com.gongsung.company.query.QueryCompanyUseCase
import com.gonsung.company.Company
import com.gonsung.company.CompanyIdentity
import com.gonsung.company.CompanyProps
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val queryCompanyPersist: QueryCompanyPersist,
    private val commandCompanyPersist: CommandCompanyPersist,
) : CommandCompanyUseCase, QueryCompanyUseCase {
    override fun getCompanyById(id: CompanyIdentity): Company {
        return queryCompanyPersist.getCompanyById(id.id)
    }

    override fun creatCompany(companyProps: CompanyProps): Company {
        return commandCompanyPersist.createCompany(companyProps)
    }

    override fun deleteCompany(id: CompanyIdentity): Boolean {
        return commandCompanyPersist.deleteCompanyById(id.id)
    }

    override fun updateCompany(company: Company): Company {
        return commandCompanyPersist.updateCompany(company)
    }
}
