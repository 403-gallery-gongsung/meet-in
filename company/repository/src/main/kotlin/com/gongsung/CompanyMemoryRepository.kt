package com.gongsung

import com.gongsung.entity.CompanyEntity
import com.gongsung.port.CommandCompanyPort
import com.gongsung.port.LookCompanyPort
import com.gonsung.company.Company
import com.gonsung.company.CompanyProps

class CompanyMemoryRepository : LookCompanyPort, CommandCompanyPort {
    private val repository = mutableMapOf<Long, CompanyEntity>()
    override fun getCompanyById(id: Long): Company {
        return repository[id] ?: throw Exception("Not Found")
    }

    override fun creatCompany(companyProps: CompanyProps): Company {
        return synchronized(repository) {
            companyProps.let(CompanyEntity::ofProps)
                .copy(
                    id = repository.size.toLong() + 1,
                ).also {
                    repository[it.id!!] = it
                }
        }
    }

    override fun deleteCompanyById(id: Long): Boolean {
        return repository.remove(id) != null
    }
}
