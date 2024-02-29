package com.gongsung.auth

import com.gongsung.auth.command.CommandUserUseCase
import com.gongsung.auth.persist.command.CommandUserPersist
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val commandUserPersist: CommandUserPersist,
) : CommandUserUseCase {
    @Transactional
    override fun createUser(props: UserProps): User {
        return commandUserPersist.createUser(props)
    }
}
