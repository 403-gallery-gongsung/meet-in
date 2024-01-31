package com.gongsung.user.query

import com.gongsung.user.User
import com.gongsung.user.UserIdentity
import com.gongsung.user.UserProps

interface QueryUseCase {
    fun getById(id: UserIdentity): User
}