package com.gongsung.auth

import com.gongsung.auth.command.CommandCompanyUseCase
import com.gongsung.auth.persist.command.CommandCompanyPersist
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CompanyService(
    private val commandCompanyPersist: CommandCompanyPersist,
) : CommandCompanyUseCase {
    @Transactional
    override fun createCompany(props: CompanyProps): Company {
        return commandCompanyPersist.createCompany(props)
    }
}
