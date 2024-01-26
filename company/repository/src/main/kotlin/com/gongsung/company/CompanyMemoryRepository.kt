package com.gongsung.company

import com.gongsung.company.entity.CompanyEntity
import com.gongsung.company.persist.CommandCompanyPersist
import com.gongsung.company.persist.QueryCompanyPersist
import com.gonsung.company.Company
import com.gonsung.company.CompanyProps
import jakarta.persistence.EntityNotFoundException

class CompanyMemoryRepository : CommandCompanyPersist, QueryCompanyPersist {
    private val repository = mutableMapOf<Long, Company>()
    override fun getCompanyById(id: Long): Company {
        return repository[id] ?: throw EntityNotFoundException("[Company-${id}] Not Exist")
    }

    override fun createCompany(companyProps: CompanyProps): Company {
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

    override fun updateCompany(company: Company): Company {
        return synchronized(repository) {
            if (repository[company.companyIdentity] == null) throw Exception("[Company-${company.companyIdentity}] Not Exist")
            repository[company.companyIdentity] = company
            company
        }
    }
}
