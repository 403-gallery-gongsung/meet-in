package com.gongsung.feed

import com.gongsung.feed.entity.Like
import com.gongsung.feed.entity.LikePK
import org.springframework.stereotype.Repository

@Repository
internal class LikeMemoryRepository : CommandLikePersist {

    private val repository = mutableMapOf<LikePK, LikeModel>()

    override fun create(feedId: Long, userId: Long) {
        val key = LikePK(userId, feedId)
        repository[key] = Like.of(key)
    }

    override fun delete(feedId: Long, userId: Long) {
        val key = LikePK(userId, feedId)
        repository.remove(key)
    }
}
