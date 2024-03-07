package com.gongsung.auth.dto

import com.gongsung.auth.AccountProps
import com.gongsung.auth.AccountType

class AccountPropsRequest (
    override val loginId: String,
    override val password: String,
    override val type: AccountType

): AccountProps