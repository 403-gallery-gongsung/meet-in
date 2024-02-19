package com.gongsung.user

import com.gongsung.user.persist.QueryUserPersist
import com.gongsung.user.query.QueryUserUseCase

class UserLookUpService(
    private val queryPersist: QueryUserPersist
) : QueryUserUseCase {
    override fun getUserById(id: UserIdentity): User {
        return queryPersist.getUserById(id.userIdentity)
    }
}