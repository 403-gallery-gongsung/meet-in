package com.gongsung.user.dto

import com.gongsung.user.UserProps
import com.gongsung.user.enums.Gender
import jakarta.validation.constraints.Email
import java.time.LocalDate

data class UserInternalRequest(
    override val loginId: String,
    @Email
    override val email: String,
    override val password: String,
    override val name: String,
    override val birthDate: LocalDate,
    override val gender: Gender,
    override val introduce: String
) : UserProps