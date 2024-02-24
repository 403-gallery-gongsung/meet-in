package com.gongsung.user.dto

import com.gongsung.user.ConnectionRequestProps
import com.gongsung.user.enums.ConnectionRequestStatus

data class ConnectionRequestDto(
    override val fromUserId: Long,
    override val toUserId: Long,
    override val status: ConnectionRequestStatus
) : ConnectionRequestProps
