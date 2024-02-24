package com.gongsung.auth.command

import com.gongsung.auth.AccountProps
import com.gongsung.auth.Company
import com.gongsung.auth.CompanyProps
import com.gongsung.auth.CompanyPropsImpl
import com.gongsung.auth.TokenInfo
import com.gongsung.auth.User
import com.gongsung.auth.UserProps

interface CommandAuthUseCase {
    fun signIn(props: AccountProps): TokenInfo
}