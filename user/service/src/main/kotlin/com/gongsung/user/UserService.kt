package com.gongsung.user

import com.gongsung.user.command.CommandUseCase
import com.gongsung.user.persist.CommandPersist
import com.gongsung.user.persist.QueryPersist
import com.gongsung.user.query.QueryUseCase
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService(
    private val commandPersist: CommandPersist
) : CommandUseCase {
    override fun create(user: UserProps): User {
        return commandPersist.create(user)
    }

    override fun delete(id: UserIdentity): Boolean {
        return commandPersist.delete(id.userIdentity)
    }

    override fun update(user: User): User {
        return commandPersist.update(user)
    }
}