package com.gongsung.follow.query

import com.gongsung.user.Follow
import com.gongsung.user.UserIdentity

interface FollowQueryUseCase {
    fun getByFromUserIdAndToUserId(fromUserIdentity: UserIdentity, toUserIdentity: UserIdentity): Follow?
    fun getByFromUserIdAndToCompanyId(
        fromUserIdentity: UserIdentity,
        toCompanyIdentity: Long
    ): Follow? // toCompanyIdentity: Long을 CompanyIdentity로 바꿔야하는가?
}
