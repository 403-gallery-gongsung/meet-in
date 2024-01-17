package com.gongsung.user.repository

import com.gongsung.user.domain.entity.UserEntity
import com.gongsung.user.domain.enums.Role
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByLoginId(loginId: String): UserEntity?
}

interface MemberRoleRepository : JpaRepository<Role, Long>
