package com.gongsung.user.persist

import com.gongsung.user.User
import com.gongsung.user.UserProps

interface CommandUserPersist {
    fun createUser(userProps: UserProps): User
    fun deleteUser(id: Long): Boolean

    fun updateUser(user: User): User
}
