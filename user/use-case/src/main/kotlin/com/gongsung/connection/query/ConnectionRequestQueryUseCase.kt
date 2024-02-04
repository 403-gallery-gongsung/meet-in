package com.gongsung.connection.query

import com.gongsung.user.ConnectionRequest
import com.gongsung.user.UserIdentity

interface ConnectionRequestQueryUseCase {
    fun getAllConnectionByUserId(userIdentity: UserIdentity): List<ConnectionRequest>
    fun getByFromUserIdAndToUserId(fromUserIdentity: UserIdentity, toUserIdentity: UserIdentity): ConnectionRequest?
}
