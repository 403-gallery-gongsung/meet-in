package com.gongsung.auth

import com.gongsung.auth.entity.CompanyEntity
import com.gongsung.auth.entity.QAccountEntity.accountEntity
import com.gongsung.auth.entity.QCompanyEntity.companyEntity
import com.gongsung.auth.persist.command.CommandCompanyPersist
import com.gongsung.auth.persist.query.QueryCompanyPersist
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class CompanyRepository(
    @PersistenceContext
    private val entityManager: EntityManager,
    private val jpaQueryFactory: JPAQueryFactory
) : QueryCompanyPersist, CommandCompanyPersist {
    override fun getCompanyById(id: Long): Company =
        jpaQueryFactory.select(
            companyEntity.loginId,
            companyEntity.password,
            companyEntity.name,
            companyEntity.introduce,
        )
            .from(companyEntity)
            .join(accountEntity).on(companyEntity.id.eq(accountEntity.id))
            .fetchOne()
            ?.let {
                Company.of(
                    CompanyIdentity.of(id),
                    CompanyProps.of(
                        loginId = it.get(companyEntity.loginId)!!,
                        password = it.get(companyEntity.password)!!,
                        name = it.get(companyEntity.name)!!,
                        website = it.get(companyEntity.website),
                        introduce = it.get(companyEntity.introduce),
                    ),
                )
            } ?: throw NoSuchElementException("Company not Found With Id: $id")

    override fun createCompany(company: CompanyProps): Company =
        company.let(CompanyEntity::ofProps)
            .also { entityManager.persist(it) }
}