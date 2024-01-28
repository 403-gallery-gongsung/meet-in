package com.gongsung.feed

interface QueryFeedPersist {

    fun findById(id: Long): FeedModel
}
