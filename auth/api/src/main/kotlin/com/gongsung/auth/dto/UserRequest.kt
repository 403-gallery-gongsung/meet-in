package com.gongsung.auth.dto

import com.gongsung.auth.Gender
import com.gongsung.auth.UserProps
import java.time.Instant
import java.time.LocalDate

class UserRequest(
    override val loginId: String,
    override var password: String,
    override val email: String,
    override var name: String,
    override var birthDate: LocalDate?,
    override var gender: Gender?,
    override var introduce: String?,
):UserProps