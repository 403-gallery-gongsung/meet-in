package com.gongsung.user.persist

import com.gongsung.user.User
import com.gongsung.user.UserProps

interface CommandUserPersist {
    fun deleteUser(id: Long): Boolean
    fun updateUser(user: User): User
}