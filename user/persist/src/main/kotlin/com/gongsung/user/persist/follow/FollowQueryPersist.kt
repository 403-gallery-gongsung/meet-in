package com.gongsung.user.persist.follow

import com.gongsung.user.Follow
import com.gongsung.user.UserIdentity

interface FollowQueryPersist {
    fun getAllFollowingByUserId(userId: UserIdentity): List<Follow>
    fun getFollowByFromUserIdAndToUserId(fromUserId: UserIdentity, toUserId: UserIdentity): Follow?
    fun getFollowByFromUserIdAndToCompanyId(
        fromUserId: UserIdentity,
        toCompanyId: Long
    ): Follow? // companyId is Long? or companyId is CompanyIdentity?
}
