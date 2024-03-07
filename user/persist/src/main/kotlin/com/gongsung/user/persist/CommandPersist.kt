package com.gongsung.user.persist

import com.gongsung.user.User
import com.gongsung.user.UserProps

interface CommandPersist {
    fun create(user: UserProps): User
    fun delete(id: Long): Boolean
    fun update(user: User): User
}
