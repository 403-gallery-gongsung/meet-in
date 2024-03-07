package com.gongsung.user.query

import com.gongsung.user.User
import com.gongsung.user.UserIdentity

interface QueryUseCase {
    fun getById(id: UserIdentity): User
}
