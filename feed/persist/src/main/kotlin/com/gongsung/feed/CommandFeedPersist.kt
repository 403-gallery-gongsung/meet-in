package com.gongsung.feed

interface CommandFeedPersist {

    fun create(feedProps: FeedProps): FeedModel

    fun update(feedId: Long, feedProps: FeedProps): FeedModel

    fun delete(id: Long)

    fun increaseLikes(feedId: Long)

    fun decreaseLikes(feedId: Long)
}
