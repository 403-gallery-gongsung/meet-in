package com.gongsung.connection.command

import com.gongsung.user.ConnectionRequest
import com.gongsung.user.ConnectionRequestProps
import com.gongsung.user.UserIdentity

interface ConnectionRequestCommandUseCase {
    fun create(fromUserIdentity: UserIdentity, toUserIdentity: UserIdentity): ConnectionRequest
    fun update(connectionRequestProps: ConnectionRequestProps): ConnectionRequest
}
