package com.gongsung.auth.entity

import com.gongsung.auth.Account
import com.gongsung.auth.AccountProps
import com.gongsung.auth.AccountType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.boot.autoconfigure.domain.EntityScan

@Entity
@Table(name = "Account")
class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = JpaConstant.NOT_YET,
    override val loginId: String = "",
    override val password: String = "",
    override val type: AccountType = AccountType.USER

) : Account {
    companion object {
        fun ofProps(accountProps: AccountProps) = AccountEntity(
            loginId = accountProps.loginId,
            password = accountProps.password,
            type = accountProps.type
        )
    }

    override val accountIdentity: Long
        get() = id ?: throw RuntimeException("There is no user")
}

object JpaConstant {
    val NOT_YET = -1L
}