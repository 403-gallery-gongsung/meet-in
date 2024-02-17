package com.gongsung.user

interface Follow : FollowIdentity, FollowToUserProps, FollowToCompanyProps

interface FollowIdentity {
    val id: Long
}

interface FollowToUserProps {
    val fromUserId: Long
    val toUserId: Long
}

interface FollowToCompanyProps {
    val fromUserId: Long
    val toCompanyId: Long
}
