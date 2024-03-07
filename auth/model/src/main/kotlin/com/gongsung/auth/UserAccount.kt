package com.gongsung.auth


interface UserAccount : AccountIdentity, UserAccountProps {
    companion object {
        fun of(
            identity: AccountIdentity,
            props: UserAccountProps
        ) = UserAccountImpl(
            identity.accountIdentity,
            accountProps = props.accountProps,
            userProps = props.userProps,
        )
    }
}


interface UserAccountProps {
    val accountProps: AccountProps
    val userProps: UserProps

    companion object {
        fun of(
            accountProps: AccountProps,
            userProps: UserProps,
        ) = UserAccountPropsImpl(
            accountProps,
            userProps,
        )
    }
}

class UserAccountImpl(
    override val accountIdentity: Long,
    override val accountProps: AccountProps,
    override val userProps: UserProps
) : UserAccount

class UserAccountPropsImpl(
    override val accountProps: AccountProps,
    override val userProps: UserProps
) : UserAccountProps
