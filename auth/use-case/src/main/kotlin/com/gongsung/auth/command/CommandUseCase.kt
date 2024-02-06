package com.gongsung.auth.command

import com.gongsung.auth.Account
import com.gongsung.auth.AccountProps
import com.gongsung.auth.security.authority.TokenInfo

interface CommandUseCase {
    fun signIn(accountProps: AccountProps) : TokenInfo
}