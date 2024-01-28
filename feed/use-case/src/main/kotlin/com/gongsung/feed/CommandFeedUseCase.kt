package com.gongsung.feed

interface CommandFeedUseCase {

    fun create(feedProps: FeedProps): FeedModel

    fun update(feedId: Long, feedProps: FeedProps): FeedModel

    fun delete(id: FeedIdentity)
}
