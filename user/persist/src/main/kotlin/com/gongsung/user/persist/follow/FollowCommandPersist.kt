package com.gongsung.user.persist.follow

import com.gongsung.user.Follow
import com.gongsung.user.FollowToCompanyProps
import com.gongsung.user.FollowToUserProps

interface FollowCommandPersist {
    fun createFollowToUser(followToUserProps: FollowToUserProps): Follow
    fun createFollowToCompany(followToCompanyProps: FollowToCompanyProps): Follow
    fun deleteFollowToUser(followToUserProps: FollowToUserProps): Boolean
    fun deleteFollowToCompany(followToCompanyProps: FollowToCompanyProps): Boolean

}
