package com.gongsung.user

import com.gongsung.user.persist.CommandPersist
import com.gongsung.user.persist.QueryPersist
import org.springframework.stereotype.Repository

@Repository
class UserRepository : CommandPersist, QueryPersist {
    override fun create(user: UserProps): User {
        TODO("save User in database")
    }

    override fun delete(id: Long): Boolean {
        TODO("delete user in database by id")
    }

    override fun update(user: User): User {
        TODO("update user in database by user parameter (setter)")
    }

    override fun getById(id: Long): User {
        TODO("get user in database by id")
    }
}
