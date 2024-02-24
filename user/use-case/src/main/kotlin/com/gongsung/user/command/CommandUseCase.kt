package com.gongsung.user.command

import com.gongsung.user.User
import com.gongsung.user.UserIdentity
import com.gongsung.user.UserProps

interface CommandUseCase {
    fun create(userProps: UserProps): User
    fun delete(id: UserIdentity): Boolean
    fun update(user: User): User
}
