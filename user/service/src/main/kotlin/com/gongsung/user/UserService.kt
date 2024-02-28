package com.gongsung.user

import com.gongsung.user.command.CommandUserUseCase
import com.gongsung.user.persist.CommandUserPersist
import org.springframework.stereotype.Service

class UserService(
    private val commandPersist: CommandUserPersist
) : CommandUserUseCase {
    override fun deleteUser(id: UserIdentity): Boolean {
        return commandPersist.deleteUser(id.userIdentity)
    }

    override fun updateUser(user: User): User {
        return commandPersist.updateUser(user)
    }
}