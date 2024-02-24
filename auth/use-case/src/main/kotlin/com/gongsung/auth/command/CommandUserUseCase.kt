package com.gongsung.auth.command

import com.gongsung.auth.User
import com.gongsung.auth.UserProps

interface CommandUserUseCase {
    fun createUser(props: UserProps): User
}