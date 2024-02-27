package com.gongsung.auth

interface Account : AccountIdentity, AccountProps {
    companion object {
        fun of(identity: AccountIdentity, props: AccountProps) = AccountImpl(
            identity.accountIdentity,
            props.loginId,
            props.password,
            props.type
        )
    }
}

interface AccountProps {
    val loginId: String
    val password: String
    val type: AccountType

    companion object {
        fun of(
            loginId: String,
            password: String,
            type: AccountType
        ): AccountProps = AccountPropsImpl(loginId, password,type)
    }
}

interface AccountIdentity {
    val accountIdentity: Long

    companion object {
        fun of(accountIdentity: Long): AccountIdentity = AccountIdentityImpl(accountIdentity)
    }
}

class AccountImpl(
    override val accountIdentity: Long,
    override val loginId: String,
    override val password: String,
    override val type: AccountType,
) : Account

class AccountPropsImpl(
    override val loginId: String,
    override val password: String,
    override val type: AccountType,
) : AccountProps

class AccountIdentityImpl(
    override val accountIdentity: Long
) : AccountIdentity