package com.gongsung.user

import com.gongsung.user.entity.FollowEntity
import com.gongsung.user.entity.base.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface JpaFollowRepository : JpaRepository<FollowEntity, Long> {

    fun findFirstByFromUserIdAndToUserIdOrderByIdDesc(fromUserId: Long, toUserId: Long): FollowEntity?
    fun findFirstByFromUserIdAndToCompanyIdOrderByIdDesc(fromUserId: Long, toCompanyId: Long): FollowEntity?
    fun findAllByFromUserIdAndStatus(fromUserId: Long, status: EntityStatus): List<FollowEntity>
}
