package com.gongsung.user

import com.gongsung.user.persist.QueryPersist
import com.gongsung.user.query.QueryUseCase
import org.springframework.stereotype.Service

@Service
class UserLookUpService(
    private val queryPersist: QueryPersist
) : QueryUseCase {
    override fun getById(id: UserIdentity): User {
        return queryPersist.getById(id.userIdentity)
    }
}