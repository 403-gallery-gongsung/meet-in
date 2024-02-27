package com.gongsung.auth.entity

import com.gongsung.auth.Account
import com.gongsung.auth.AccountProps
import com.gongsung.auth.AccountType
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.Table

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Table(name = "Account")
open class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = JpaConstant.NOT_YET,
    override val loginId: String = "",
    override val password: String = "",

    @Enumerated(EnumType.STRING)
    override val type: AccountType = AccountType.FORBID,
) : Account {
    companion object {
        fun ofProps(accountProps: AccountProps) = AccountEntity(
            loginId = accountProps.loginId,
            password = accountProps.password,
            type = accountProps.type
        )
    }

    override val accountIdentity: Long
        get() = id
}

object JpaConstant {
    val NOT_YET = -1L
}