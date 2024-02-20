package com.gongsung.auth.command

import com.gongsung.auth.AccountProps
import com.gongsung.auth.TokenInfo

interface CommandUseCase {
    fun signIn(accountProps: AccountProps) : TokenInfo
}