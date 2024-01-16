package com.gongsung

import com.gongsung.command.CommandCompanyUseCase
import com.gongsung.look.LookCompanyUseCase
import com.gongsung.port.CommandCompanyPort
import com.gongsung.port.LookCompanyPort
import com.gonsung.company.Company
import com.gonsung.company.CompanyIdentity

class CompanyService(
    private val lookCompanyPort: LookCompanyPort,
    private val commandCompanyPort: CommandCompanyPort
) : CommandCompanyUseCase, LookCompanyUseCase {
    override fun getCompanyById(id: CompanyIdentity): Company {
        return lookCompanyPort.getCompanyById(id.companyIdentity)
    }

    override fun creatCompany(companyDTO: Company): Company {
        return commandCompanyPort.creatCompany(
            companyDTO,
        )
    }

    override fun deleteCompany(id: CompanyIdentity): Boolean {
        return commandCompanyPort.deleteCompanyById(id.companyIdentity)
    }

    override fun updateCompany(companyDTO: Company): Company {
        TODO("Not yet implemented")
    }
}
