package com.gongsung.auth.query

import com.gongsung.auth.Account
import com.gongsung.auth.AccountIdentity

interface QueryUseCase {
    fun getAccountById(id: AccountIdentity) : Account
}