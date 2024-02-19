package com.gongsung.user.persist

import com.gongsung.user.User

interface QueryUserPersist {
    fun getUserById(id: Long): User
}