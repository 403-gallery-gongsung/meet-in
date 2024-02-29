package com.gongsung.auth.persist.command

import com.gongsung.auth.User
import com.gongsung.auth.UserProps

interface CommandUserPersist {
    fun createUser(user: UserProps): User
}
