package com.gongsung.user.query

import com.gongsung.user.User
import com.gongsung.user.UserIdentity

interface QueryUserUseCase {
    fun getUserById(id: UserIdentity): User
}
