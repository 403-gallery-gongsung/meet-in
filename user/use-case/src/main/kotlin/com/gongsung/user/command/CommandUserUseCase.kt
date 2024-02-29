package com.gongsung.user.command

import com.gongsung.user.User
import com.gongsung.user.UserIdentity

interface CommandUserUseCase {
    fun deleteUser(id: UserIdentity): Boolean

    fun updateUser(user: User): User
}
