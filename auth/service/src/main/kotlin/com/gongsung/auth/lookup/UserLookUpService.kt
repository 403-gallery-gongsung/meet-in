package com.gongsung.auth.lookup

import com.gongsung.auth.User
import com.gongsung.auth.UserIdentity
import com.gongsung.auth.persist.query.QueryUserPersist
import com.gongsung.auth.query.QueryUserUseCase

class UserLookUpService(
    private val queryUserPersist: QueryUserPersist
) : QueryUserUseCase {
    override fun getUserById(id: UserIdentity): User {
        return queryUserPersist.getUserById(id.userIdentity)
    }
}