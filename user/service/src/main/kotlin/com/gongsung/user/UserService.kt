package com.gongsung.user

import com.gongsung.user.command.CommandUserUseCase
import com.gongsung.user.persist.CommandUserPersist
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val commandPersist: CommandUserPersist,
) : CommandUserUseCase {

    @Transactional
    override fun createUser(userProps: UserProps): User {
        return commandPersist.createUser(userProps)
    }

    @Transactional
    override fun deleteUser(id: UserIdentity): Boolean {
        return commandPersist.deleteUser(id.userIdentity)
    }
    @Transactional
    override fun updateUser(user: User): User {
        return commandPersist.updateUser(user)
    }
}
