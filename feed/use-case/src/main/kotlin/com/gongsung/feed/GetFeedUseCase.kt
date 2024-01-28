package com.gongsung.feed

interface GetFeedUseCase {

    fun getFeed(id: FeedIdentity): FeedModel
}
