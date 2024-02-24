package com.gongsung.user.persist.connection

import com.gongsung.user.ConnectionRequest
import com.gongsung.user.ConnectionRequestProps

interface ConnectionRequestCommandPersist {
    fun create(connectionRequestProps: ConnectionRequestProps): ConnectionRequest
    fun update(connectionRequestProps: ConnectionRequestProps): ConnectionRequest
}
