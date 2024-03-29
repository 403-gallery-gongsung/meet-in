package com.gongsung.user.command

import com.gongsung.user.User
import com.gongsung.user.UserIdentity
import com.gongsung.user.UserProps

interface CommandUserUseCase {
    fun createUser(userProps: UserProps): User
    fun deleteUser(id: UserIdentity): Boolean

    fun updateUser(user: User): User
}
