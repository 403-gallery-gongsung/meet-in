package com.gongsung.auth.command

import com.gongsung.auth.AccountProps
import com.gongsung.auth.TokenInfo

interface SignInUseCase {
    fun signIn(props: AccountProps): TokenInfo
}
