package com.gongsung.feed

interface CommandFeedUseCase {

    fun create(feedProps: FeedProps): FeedModel

    fun update(feedId: Long, feedProps: FeedProps): FeedModel

    fun delete(id: FeedIdentity)

    fun like(feedId: Long, userId: Long)

    fun dislike(feedId: Long, userId: Long)
}
