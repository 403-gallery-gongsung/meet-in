package com.gongsung.user

import com.gongsung.user.entity.FollowEntity
import com.gongsung.user.entity.base.EntityStatus
import com.gongsung.user.persist.follow.FollowCommandPersist
import com.gongsung.user.persist.follow.FollowQueryPersist
import org.springframework.stereotype.Repository

@Repository
class FollowRepository(
    private val jpaFollowRepository: JpaFollowRepository,
) : FollowCommandPersist, FollowQueryPersist {
    override fun getAllFollowingByUserId(userId: UserIdentity): List<Follow> {
        return jpaFollowRepository.findAllByFromUserIdAndStatus(userId.userIdentity, EntityStatus.NORMAL)
    }

    override fun getFollowByFromUserIdAndToUserId(fromUserId: UserIdentity, toUserId: UserIdentity): FollowEntity? {
        return jpaFollowRepository.findFirstByFromUserIdAndToUserIdOrderByIdDesc(
            fromUserId.userIdentity,
            toUserId.userIdentity,
        )
    }

    override fun getFollowByFromUserIdAndToCompanyId(fromUserId: UserIdentity, toCompanyId: Long): FollowEntity? {
        return jpaFollowRepository.findFirstByFromUserIdAndToCompanyIdOrderByIdDesc(
            fromUserId.userIdentity,
            toCompanyId,
        )
    }

    override fun createFollowToUser(followToUserProps: FollowToUserProps): Follow {
        return jpaFollowRepository.save(
            FollowEntity.ofProps(followToUserProps),
        )
    }

    override fun createFollowToCompany(followToCompanyProps: FollowToCompanyProps): Follow {
        return jpaFollowRepository.save(
            FollowEntity.ofProps(followToCompanyProps),
        )
    }

    override fun deleteFollowToUser(followToUserProps: FollowToUserProps): Boolean {
        return getFollowByFromUserIdAndToUserId(
            UserIdentity.of(followToUserProps.fromUserId),
            UserIdentity.of(followToUserProps.toUserId),
        )?.also {
            jpaFollowRepository.save(it.apply { status = EntityStatus.DELETED })
        } != null
    }

    override fun deleteFollowToCompany(followToCompanyProps: FollowToCompanyProps): Boolean {
        return getFollowByFromUserIdAndToCompanyId(
            UserIdentity.of(followToCompanyProps.fromUserId),
            followToCompanyProps.toCompanyId,
        )?.also {
            jpaFollowRepository.save(it.apply { status = EntityStatus.DELETED })
        } != null
    }
}
