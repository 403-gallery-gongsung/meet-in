package com.gongsung.auth

interface Account : AccountIdentity, AccountProps {
    companion object {
        fun of(identity: AccountIdentity, props: AccountProps) = AccountImpl(
            identity.accountIdentity,
            props.loginId,
            props.password,
        )
    }
}

interface AccountProps {
    val loginId: String
    val password: String

    companion object {
        fun of(
            loginId: String,
            password: String,
        ): AccountProps = AccountPropsImpl(loginId, password)
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
) : Account

class AccountPropsImpl(
    override val loginId: String,
    override val password: String,
) : AccountProps

class AccountIdentityImpl(
    override val accountIdentity: Long
) : AccountIdentity