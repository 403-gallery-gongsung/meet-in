package com.gongsung.auth

import java.time.LocalDate

interface User : UserIdentity, UserProps {
    companion object {
        fun of(
            identity: UserIdentity,
            props: UserProps,
        ) = UserImpl(
            userIdentity = identity.userIdentity,
            accountId = props.accountId,
            email = props.email,
            name = props.name,
            birthDate = props.birthDate,
            gender = props.gender,
            introduce = props.introduce,
        )
    }
}

interface UserIdentity {
    val userIdentity: Long

    companion object {
        fun of(userIdentity: Long): UserIdentity = UserIdentityImpl(userIdentity)
    }
}

interface UserProps {
    val accountId: Long
    val email: String
    var name: String
    var birthDate: LocalDate?
    var gender: Gender?
    var introduce: String?

    companion object {
        fun of(
            accountId: Long,
            email: String,
            name: String,
            birthDate: LocalDate?,
            gender: Gender?,
            introduce: String?,
        ): UserProps = UserPropsImpl(accountId,email, name, birthDate, gender, introduce)
    }
}

class UserImpl(
    override val userIdentity: Long,
    override val accountId: Long,
    override val email: String,
    override var name: String,
    override var birthDate: LocalDate?,
    override var gender: Gender?,
    override var introduce: String?,
) : User

class UserPropsImpl (
    override val accountId: Long,
    override val email: String,
    override var name: String,
    override var birthDate: LocalDate?,
    override var gender: Gender?,
    override var introduce: String?,
) : UserProps

class UserIdentityImpl(
    override val userIdentity: Long,
) : UserIdentity
