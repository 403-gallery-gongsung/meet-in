package com.gongsung.auth


import com.gongsung.auth.entity.QAccountEntity.accountEntity
import com.gongsung.auth.entity.QCompanyEntity.companyEntity
import com.gongsung.auth.entity.QUserEntity.userEntity
import com.gongsung.auth.persist.query.QueryAuthPersist
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.security.core.userdetails.UsernameNotFoundException

class AuthRepository(
    private val jpaQueryFactory: JPAQueryFactory
) : QueryAuthPersist {
    override fun findByLoginId(loginId: String): Account {
        val result = jpaQueryFactory.selectFrom(accountEntity)
            .where(accountEntity.loginId.eq(loginId))
            .fetchOne() ?: throw UsernameNotFoundException("Account not found by loginId: $loginId")

/*
        val entity = when (result.type) {
            AccountType.USER -> userEntity
            AccountType.COMPANY -> companyEntity
            AccountType.FORBID -> throw IllegalStateException("AccountType is not specified for given loginId : $loginId")
        }
*/


        return when (result.type) {
            AccountType.USER -> jpaQueryFactory.select(
                userEntity.loginId,
                userEntity.password,
            )
                .from(userEntity)
                .join(userEntity).on(accountEntity.id.eq(userEntity.id))
                .fetchOne()
                ?.let {
                    Account.of(
                        AccountIdentity.of(result.id),
                        AccountProps.of(
                            loginId = it.get(userEntity.loginId)!!,
                            password = it.get(userEntity.password)!!,
                            type = result.type,
                        ),
                    )
                }!!

            AccountType.COMPANY -> jpaQueryFactory.select(
                companyEntity.loginId,
                companyEntity.password,
            )
                .from(companyEntity)
                .join(companyEntity).on(accountEntity.id.eq(companyEntity.id))
                .fetchOne()
                ?.let {
                    Account.of(
                        AccountIdentity.of(result.id),
                        AccountProps.of(
                            loginId = it.get(companyEntity.loginId)!!,
                            password = it.get(companyEntity.password)!!,
                            type = result.type,
                        ),
                    )
                }!!

            AccountType.FORBID -> throw IllegalStateException("AccountType is not specified for given loginId : $loginId")
        }
    }
}