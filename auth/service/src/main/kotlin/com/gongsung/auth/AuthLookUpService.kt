package com.gongsung.auth

import com.gongsung.auth.persist.QueryAuthPersist
import com.gongsung.auth.query.QueryAuthUseCase

class AuthLookUpService(
    private val queryAuthPersist: QueryAuthPersist
) : QueryAuthUseCase {
    override fun getAccountById(id: AccountIdentity): Account {
        return queryAuthPersist.getAccountById(id.accountIdentity)
    }
}