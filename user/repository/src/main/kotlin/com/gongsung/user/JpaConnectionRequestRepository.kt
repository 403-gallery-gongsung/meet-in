package com.gongsung.user

import com.gongsung.user.entity.ConnectionRequestEntity
import com.gongsung.user.enums.ConnectionRequestStatus
import org.springframework.data.jpa.repository.JpaRepository

interface JpaConnectionRequestRepository : JpaRepository<ConnectionRequestEntity, Long> {
    fun findByFromUserIdAndToUserId(fromUserId: Long, toUserId: Long): ConnectionRequestEntity?
    fun findAllByFromUserIdAndStatus(fromUserId: Long, status: ConnectionRequestStatus): List<ConnectionRequestEntity>
}
