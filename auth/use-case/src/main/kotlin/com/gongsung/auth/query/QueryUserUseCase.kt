package com.gongsung.auth.query

import com.gongsung.auth.User
import com.gongsung.auth.UserIdentity

interface QueryUserUseCase {
    fun getUserById(id: UserIdentity) : User
}