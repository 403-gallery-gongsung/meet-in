package com.gongsung.company

import com.gongsung.company.entity.CompanyEntity
import com.gonsung.company.Company
import com.gonsung.company.CompanyProps
import jakarta.persistence.EntityNotFoundException

class CompanyMemoryRepository {
    private val repository = mutableMapOf<Long, CompanyEntity>()
    fun getCompanyById(id: Long): Company {
        return repository[id] ?: throw EntityNotFoundException("[Company-${id}] Not Exist")
    }

    fun createCompany(companyProps: CompanyProps): Company {
        return synchronized(repository) {
            companyProps.let(CompanyEntity::ofProps)
                .copy(
                    id = repository.size.toLong() + 1,
                ).also {
                    repository[it.id!!] = it
                }
        }
    }

    fun deleteCompanyById(id: Long): Boolean {
        return repository.remove(id) != null
    }

    fun updateCompany(company: Company): Company {
        return synchronized(repository) {
            repository[company.id]?.let {
                it.copy(
                    address = company.address,
                    name = company.name,
                ).also { updatedCompany ->
                    repository[company.id] = updatedCompany
                }
            } ?: throw Exception("[Company-${company.id}] Not Exist")
        }
    }
}
