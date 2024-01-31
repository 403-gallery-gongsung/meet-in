package com.gongsung.user.dto

import com.gongsung.user.UserProps
import com.gongsung.user.enums.Gender
import java.time.LocalDate

data class UserInternalRequest(
    override val loginId: String,
    override val email: String,
    override val password: String,
    override val name: String,
    override val birthDate: LocalDate,
    override val gender: Gender,
    override val introduce: String
) : UserProps