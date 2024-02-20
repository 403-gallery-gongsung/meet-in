package com.gongsung.auth.persist

import com.gongsung.auth.Account

interface QueryPersist {
    fun getAccountById(id: Long): Account
}