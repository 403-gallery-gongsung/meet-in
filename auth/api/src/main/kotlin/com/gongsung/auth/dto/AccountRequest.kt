package com.gongsung.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gongsung.auth.AccountProps
import com.gongsung.auth.AccountType
import jakarta.validation.constraints.NotBlank

data class AccountRequest(
    @field:NotBlank
    @JsonProperty("loginId")
    override val loginId: String,
    @field:NotBlank
    @JsonProperty("password")
    override val password: String,
    override val type: AccountType,
) : AccountProps
