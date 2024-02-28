package com.gongsung.feed.entity

import com.gongsung.feed.LikeModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table

@Entity
@Table(name = "like_info")
@IdClass(LikePK::class)
class Like(
    @Id
    @Column(nullable = false, updatable = false)
    override val userId: Long = -1,
    @Id
    @Column(nullable = false, updatable = false)
    override val feedId: Long = -1,
) : LikeModel {

    companion object {
        fun of(key: LikePK) = Like(
            userId = key.userId,
            feedId = key.feedId
        )
    }
}
