package com.gongsung.user.dto

import com.gongsung.user.FollowToUserProps

data class FollowToUserRequestDto(
    override val fromUserId: Long,
    override val toUserId: Long
) : FollowToUserProps {
}
