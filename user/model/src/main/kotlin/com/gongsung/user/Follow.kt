package com.gongsung.user

interface Follow : FollowIdentity, FollowToUserProps, FollowToCompanyProps

interface FollowIdentity {
    val id: Long
}

interface FollowToUserProps {
    val fromUserId: Long
    val toUserId: Long
    companion object{
        fun of(fromUserId: Long, toUserId: Long): FollowToUserProps {
            return FollowToUserPropsImpl(fromUserId, toUserId)
        }
    }
}

interface FollowToCompanyProps {
    val fromUserId: Long
    val toCompanyId: Long
}

data class FollowToUserPropsImpl(
    override val fromUserId: Long,
    override val toUserId: Long
) : FollowToUserProps
