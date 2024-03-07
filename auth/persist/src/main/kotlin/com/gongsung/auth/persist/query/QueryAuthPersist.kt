package com.gongsung.auth.persist.query

import com.gongsung.auth.Account

interface QueryAuthPersist {
    fun findByLoginId(loginId: String): Account
}
