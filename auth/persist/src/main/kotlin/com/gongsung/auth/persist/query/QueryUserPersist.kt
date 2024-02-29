package com.gongsung.auth.persist.query

import com.gongsung.auth.User

interface QueryUserPersist {
    fun getUserById(id: Long): User
}
