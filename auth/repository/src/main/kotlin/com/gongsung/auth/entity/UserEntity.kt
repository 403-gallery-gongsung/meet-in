package com.gongsung.auth.entity

import com.gongsung.auth.AccountType
import com.gongsung.auth.Gender
import com.gongsung.auth.User
import com.gongsung.auth.UserProps
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.springframework.validation.annotation.Validated
import java.time.LocalDate

@Entity
@Validated
@Table(name = "USER")
data class UserEntity(
    @Column(nullable = false, length = 30)
    override val loginId: String = "",

    @Column(nullable = false, length = 30)
    @field:Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,15}$",
        message = "영어 대소문자 및 숫자, 특수기호가 1개 이상 포함된 8~15길이의 비밀번호여야 합니다.",
    )
    override var password: String = "",

    @field:Email(message = "Email 형식이 아닙니다.")
    @Column(nullable = false, length = 30)
    override val email: String = "",

    @Column(nullable = false, length = 10)
    override var name: String = "",

    @Temporal(TemporalType.DATE)
    override var birthDate: LocalDate? = LocalDate.now(),

    @Enumerated(EnumType.STRING)
    override var gender: Gender? = Gender.UNKNOWN,
    override var introduce: String? = ""
) : User, AccountEntity(loginId = loginId, password = password, type = AccountType.USER) {
    companion object {
        fun ofProps(userProps: UserProps) = UserEntity(
            loginId = userProps.loginId,
            password = userProps.password,
            email = userProps.email,
            name = userProps.name,
            birthDate = userProps.birthDate,
            gender = userProps.gender,
            introduce = userProps.introduce,
        )
    }

    override val userIdentity: Long
        get() = id
}