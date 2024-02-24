package com.gongsung.user.entity

import com.gongsung.user.User
import com.gongsung.user.UserProps
import com.gongsung.user.enums.Gender
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
data class UserEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    override val loginId: String,

    @Column(nullable = false, length = 30)
    override val email: String,

    @Column(nullable = false, length = 30)
    override val password: String,

    @Column(nullable = false, length = 10)
    override val name: String,

    @Temporal(TemporalType.DATE)
    override val birthDate: LocalDate,

    @Enumerated(EnumType.STRING)
    override val gender: Gender,

    override val introduce: String,
) : User {
    companion object {
        fun ofProps(userProps: UserProps) = UserEntity(
            loginId = userProps.loginId,
            email = userProps.email,
            password = userProps.password,
            name = userProps.name,
            birthDate = userProps.birthDate,
            gender = userProps.gender,
            introduce = userProps.introduce,
        )
    }

    private fun LocalDate.formatDate(): String =
        this.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

    override val userIdentity: Long
        get() = id ?: throw RuntimeException("There is no user")
}
