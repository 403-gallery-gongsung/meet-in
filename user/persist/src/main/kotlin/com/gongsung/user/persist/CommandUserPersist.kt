package com.gongsung.user.persist

import com.gongsung.user.User

interface CommandUserPersist {
    fun deleteUser(id: Long): Boolean

    fun updateUser(user: User): User
}
