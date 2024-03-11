package com.gongsung.auth.dto

import com.gongsung.auth.Gender
import com.gongsung.auth.UserAccountProps
import com.gongsung.auth.UserProps
import java.time.LocalDate

data class UserAccountRequest(
    override val accountProps: AccountPropsRequest,
    override val userProps: UserPropsRequest
) : UserAccountProps


data class UserPropsRequest(
    override val email: String,
    override var name: String,
    override var birthDate: LocalDate?,
    override var gender: Gender?,
    override var introduce: String?

) : UserProps
