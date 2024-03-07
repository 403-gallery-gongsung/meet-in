package com.gongsung.auth.persist.command

import com.gongsung.auth.Account
import com.gongsung.auth.AccountProps
import com.gongsung.auth.UserAccountProps

interface CommandAuthPersist {
    fun createAccount(accountProps: AccountProps): Account
}