package com.gongsung.user

import com.gongsung.user.enums.ConnectionRequestStatus

interface ConnectionRequest :
    ConnectionRequestIdentity,
    ConnectionRequestProps

interface ConnectionRequestIdentity {
    val id: Long

    companion object {
        fun of(connectionRequestId: Long): ConnectionRequestIdentity =
            ConnectionRequestIdentityImpl(connectionRequestId)
    }
}

interface ConnectionRequestProps {
    val fromUserId: Long
    val toUserId: Long
    val status: ConnectionRequestStatus

    companion object {
        fun of(
            fromUserId: Long,
            toUserId: Long,
            status: ConnectionRequestStatus,
        ): ConnectionRequestProps =
            ConnectionRequestPropsImpl(fromUserId, toUserId, status)
    }
}

data class ConnectionRequestIdentityImpl(
    override val id: Long,
) : ConnectionRequestIdentity

data class ConnectionRequestPropsImpl(
    override val fromUserId: Long,
    override val toUserId: Long,
    override val status: ConnectionRequestStatus,
) : ConnectionRequestProps
