package com.gongsung.auth

import com.gongsung.auth.entity.AccountEntity
import com.gongsung.auth.entity.QAccountEntity.accountEntity
import com.gongsung.auth.persist.command.CommandAuthPersist
import com.gongsung.auth.persist.query.QueryAuthPersist
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class AuthRepository(
    @PersistenceContext
    private val entityManager: EntityManager,
) : QueryAuthPersist, CommandAuthPersist, QuerydslRepositorySupport(Account::class.java) {
    override fun findByLoginId(loginId: String): Account = from(accountEntity)
        .where(accountEntity.loginId.eq(loginId))
        .fetchOne()
        ?.run {
            Account.of(
                AccountIdentity.of(accountIdentity),
                AccountProps.of(
                    loginId = this.loginId,
                    password = password,
                    type = type,
                ),
            )
        } ?: throw NoSuchElementException("No Tuple does not exist")

    override fun createAccount(accountProps: AccountProps): Account =
        accountProps.let(AccountEntity::ofProps)
            .also { entityManager.persist(it) }
}
