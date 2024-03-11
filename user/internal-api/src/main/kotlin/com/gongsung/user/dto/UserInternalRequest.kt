package com.gongsung.user.dto

import com.gongsung.user.UserProps
import com.gongsung.user.enums.Gender
import jakarta.validation.constraints.Email
import java.time.LocalDate

data class UserInternalRequest(
    override val accountId: Long,
    @Email
    override val email: String,
    override val name: String,
    override val birthDate: LocalDate,
    override val gender: Gender,
    override val introduce: String,
) : UserProps
