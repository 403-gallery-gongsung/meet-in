package com.gongsung.auth.persist.command

import com.gongsung.auth.Account
import com.gongsung.auth.AccountProps

interface CommandAuthPersist {
    fun createAccount(accountProps: AccountProps): Account
}