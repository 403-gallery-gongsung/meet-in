package com.gongsung.feed

import com.gongsung.feed.entity.Feed
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicLong

@Repository
internal class FeedMemoryRepository : CommandFeedPersist, QueryFeedPersist {

    companion object {
        var FEED_ID = AtomicLong(-1)
    }

    private val repository = mutableMapOf<Long, FeedModel>()

    override fun create(feedProps: FeedProps): FeedModel {
        val feedId = FEED_ID.getAndIncrement()
        val feed = Feed.of(feedId, feedProps)

        repository[feedId] = feed
        return feed
    }

    override fun update(feedId: Long, feedProps: FeedProps): FeedModel {
        repository[feedId] ?: throw IllegalArgumentException("Feed 조회 중 에러 발생.")

        repository[feedId] = Feed.of(feedId, feedProps)
        return repository[feedId] ?: throw IllegalStateException("Feed Update 중 에러 발생.")
    }

    override fun delete(id: Long) {

        repository.remove(id)
    }

    override fun findById(id: Long): FeedModel {

        return repository[id] ?: throw IllegalArgumentException("Feed 조회 중 에러 발생.")
    }

    override fun increaseLikes(feedId: Long) {
        val feed = repository[feedId] ?: throw IllegalArgumentException("Feed 조회 중 에러 발생.")
        feed.likeCount += 1
    }

    override fun decreaseLikes(feedId: Long) {
        val feed = repository[feedId] ?: throw IllegalArgumentException("Feed 조회 중 에러 발생.")
        feed.likeCount -= 1
    }
}
