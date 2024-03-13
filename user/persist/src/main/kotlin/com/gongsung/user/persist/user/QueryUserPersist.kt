package com.gongsung.user.persist.user

import com.gongsung.user.User

interface QueryUserPersist {
    fun getUserById(id: Long): User
}
