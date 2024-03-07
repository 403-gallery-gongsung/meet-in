package com.gongsung.user.persist

import com.gongsung.user.User

interface QueryPersist {
    fun getById(id: Long): User
}
