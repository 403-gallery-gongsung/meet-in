package com.gongsung.auth.persist

import com.gongsung.auth.Account

interface QueryAuthPersist {
    fun getAccountById(id: Long): Account
}