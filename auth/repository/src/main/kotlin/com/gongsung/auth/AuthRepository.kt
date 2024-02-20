package com.gongsung.auth

import com.gongsung.auth.persist.CommandAuthPersist
import com.gongsung.auth.persist.QueryAuthPersist
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class AuthRepository (
    @PersistenceContext
    private val entityManager: EntityManager
): CommandAuthPersist, QueryAuthPersist, QuerydslRepositorySupport(Account::class.java) {

    private val jpaQueryFactory by lazy { JPAQueryFactory(entityManager) }

    override fun getAccountById(id: Long): Account {
//        return jpaQueryFactory.selectFrom(Account)
//            .where

        TODO("Not yet implemented")
    }

}