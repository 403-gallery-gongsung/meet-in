package com.gongsung.follow

import com.gongsung.follow.command.FollowCommandUseCase
import com.gongsung.follow.query.FollowQueryUseCase
import com.gongsung.user.Follow
import com.gongsung.user.FollowToCompanyProps
import com.gongsung.user.FollowToUserProps
import com.gongsung.user.UserIdentity
import com.gongsung.user.persist.follow.FollowCommandPersist
import com.gongsung.user.persist.follow.FollowQueryPersist
import org.springframework.stereotype.Service

@Service
class FollowService(
    private val followCommandPersist: FollowCommandPersist,
    private val followQueryPersist: FollowQueryPersist,
) : FollowCommandUseCase, FollowQueryUseCase {
    override fun followToUser(followToUserProps: FollowToUserProps): Follow {
        return followCommandPersist.createFollowToUser(followToUserProps)
    }

    override fun followToCompany(followToCompanyProps: FollowToCompanyProps): Follow {
        return followCommandPersist.createFollowToCompany(followToCompanyProps)
    }

    override fun unFollowToUser(followToUserProps: FollowToUserProps): Boolean {
        return followCommandPersist.deleteFollowToUser(followToUserProps)
    }

    override fun unFollowToCompany(followToCompanyProps: FollowToCompanyProps): Boolean {
        return followCommandPersist.deleteFollowToCompany(followToCompanyProps)
    }

    override fun getByFromUserIdAndToUserId(fromUserIdentity: UserIdentity, toUserIdentity: UserIdentity): Follow? {
        return followQueryPersist.getFollowByFromUserIdAndToUserId(fromUserIdentity, toUserIdentity)
    }

    override fun getByFromUserIdAndToCompanyId(fromUserIdentity: UserIdentity, toCompanyIdentity: Long): Follow? {
        return followQueryPersist.getFollowByFromUserIdAndToCompanyId(fromUserIdentity, toCompanyIdentity)
    }
}
