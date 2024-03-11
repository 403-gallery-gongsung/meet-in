package com.gongsung.user.entity

import com.gongsung.user.User
import com.gongsung.user.UserIdentity
import com.gongsung.user.UserProps
import com.gongsung.user.enums.Gender
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.springframework.validation.annotation.Validated
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
@Validated
@Table(name = "USER")
data class UserEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = JpaConstants.NOT_YET_SAVED,
    override val accountId: Long,
    @field:Email(message = "Email 형식이 아닙니다.")
    @Column(nullable = false, length = 30)
    override val email: String = "",
    @Column(nullable = false, length = 10)
    override val name: String = "",
    @Temporal(TemporalType.DATE)
    override val birthDate: LocalDate? = LocalDate.now(),
    @Enumerated(EnumType.STRING)
    override val gender: Gender? = Gender.UNKNOWN,
    override val introduce: String? = "",
) : User {
    companion object {
        fun ofProps(userProps: UserProps) =
            UserEntity(
                accountId = userProps.accountId,
                email = userProps.email,
                name = userProps.name,
                birthDate = userProps.birthDate,
                gender = userProps.gender,
                introduce = userProps.introduce,
            )
    }

    override val userIdentity: Long
        get() = id ?: throw RuntimeException("There is no user")
}