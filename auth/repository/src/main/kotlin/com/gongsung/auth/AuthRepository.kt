package com.gongsung.auth

import com.gongsung.auth.persist.CommandPersist
import com.gongsung.auth.persist.QueryPersist

class AuthRepository : CommandPersist, QueryPersist {
    override fun getAccountById(id: Long): Account {
        TODO("Not yet implemented")
    }

}