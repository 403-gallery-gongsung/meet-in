package com.gongsung.user.persist.connection

import com.gongsung.user.ConnectionRequest
import com.gongsung.user.UserIdentity
import com.gongsung.user.enums.ConnectionRequestStatus

interface ConnectionRequestQueryPersist {
    fun getAllByUserId(userIdentity: UserIdentity, status: ConnectionRequestStatus): List<ConnectionRequest>
    fun getByFromUserIdAndToUserId(fromUserIdentity: UserIdentity, toUserIdentity: UserIdentity): ConnectionRequest?
}
