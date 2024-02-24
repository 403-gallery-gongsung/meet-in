package com.gongsung.auth

import com.gongsung.auth.entity.CompanyEntity
import com.gongsung.auth.entity.QCompanyEntity.companyEntity
import com.gongsung.auth.persist.command.CommandCompanyPersist
import com.gongsung.auth.persist.query.QueryCompanyPersist
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class CompanyRepository (
    @PersistenceContext
    private val entityManager: EntityManager
): QueryCompanyPersist,CommandCompanyPersist, QuerydslRepositorySupport(Company::class.java){

    private val jpaQueryFactory: JPAQueryFactory by lazy { JPAQueryFactory(entityManager) }
    override fun getCompanyById(id: Long): Company =
        jpaQueryFactory.selectFrom(companyEntity)
            .where(companyEntity.id.eq(id))
            .fetchOne()!!

    override fun createCompany(company: CompanyProps): Company =
        company.let (CompanyEntity::ofProps)
            .also { entityManager.persist(it) }
}