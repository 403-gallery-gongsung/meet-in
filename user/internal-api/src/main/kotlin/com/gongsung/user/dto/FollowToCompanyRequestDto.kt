package com.gongsung.user.dto

import com.gongsung.user.FollowToCompanyProps

data class FollowToCompanyRequestDto(
    override val fromUserId: Long,
    override val toCompanyId: Long
) : FollowToCompanyProps
