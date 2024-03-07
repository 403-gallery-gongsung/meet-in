package com.gongsung.follow.command

import com.gongsung.user.Follow
import com.gongsung.user.FollowToCompanyProps
import com.gongsung.user.FollowToUserProps

interface FollowCommandUseCase {
    fun followToUser(followToUserProps: FollowToUserProps): Follow
    fun followToCompany(followToCompanyProps: FollowToCompanyProps): Follow
    fun unFollowToUser(followToUserProps: FollowToUserProps): Boolean
    fun unFollowToCompany(followToCompanyProps: FollowToCompanyProps): Boolean
}
