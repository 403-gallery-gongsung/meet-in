package com.gongsung.user

import com.gongsung.user.enums.Gender
import java.time.LocalDate

interface User : UserIdentity, UserProps {
    companion object {
        fun of(
            identity: UserIdentity,
            props: UserProps,
        ) = UserImpl(
            identity.userIdentity,
            props.loginId,
            props.email,
            props.password,
            props.name,
            props.birthDate,
            props.gender,
            props.introduce,
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
    val loginId: String
    val email: String
    val password: String
    val name: String
    val birthDate: LocalDate?
    val gender: Gender?
    val introduce: String?

    companion object {
        fun of(
            loginId: String,
            email: String,
            password: String,
            name: String,
            birthDate: LocalDate,
            gender: Gender,
            introduce: String,
        ): UserProps = UserPropsImpl(loginId, email, password, name, birthDate, gender, introduce)
    }
}

data class UserIdentityImpl(
    override val userIdentity: Long,
) : UserIdentity

data class UserPropsImpl(
    override val loginId: String,
    override val email: String,
    override val password: String,
    override val name: String,
    override val birthDate: LocalDate,
    override val gender: Gender,
    override val introduce: String,
) : UserProps

data class UserImpl(
    override val userIdentity: Long,
    override val loginId: String,
    override val email: String,
    override val password: String,
    override val name: String,
    override val birthDate: LocalDate?,
    override val gender: Gender?,
    override val introduce: String?,
) : User
