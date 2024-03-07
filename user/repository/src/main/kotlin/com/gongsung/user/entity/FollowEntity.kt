package com.gongsung.user.entity

import com.gongsung.user.Follow
import com.gongsung.user.FollowToCompanyProps
import com.gongsung.user.FollowToUserProps
import com.gongsung.user.entity.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "follow")
class FollowEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = JpaConstants.NOT_YET_SAVED,
    @Column(name = "from_user_id")
    override val fromUserId: Long,
    @Column(name = "to_user_id")
    override val toUserId: Long = JpaConstants.NOT_YET_SAVED,
    @Column(name = "to_company_id")
    override val toCompanyId: Long = JpaConstants.NOT_YET_SAVED,
) : Follow, BaseEntity() {
    companion object {
        fun ofProps(props: FollowToCompanyProps) = FollowEntity(
            fromUserId = props.fromUserId,
            toCompanyId = props.toCompanyId,
        )

        fun ofProps(props: FollowToUserProps) = FollowEntity(
            fromUserId = props.fromUserId,
            toUserId = props.toUserId,
        )
    }
}
