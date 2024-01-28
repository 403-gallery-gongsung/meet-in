package com.gongsung.feed

import org.springframework.stereotype.Service

@Service
class FeedService(
    private val commandFeedPersist: CommandFeedPersist,
    private val queryFeedPersist: QueryFeedPersist,
) : CommandFeedUseCase, GetFeedUseCase {
    override fun create(feedProps: FeedProps): FeedModel {
        return commandFeedPersist.create(feedProps)
    }

    override fun update(
        feedId: Long,
        feedProps: FeedProps,
    ): FeedModel {
        return commandFeedPersist.update(feedId, feedProps)
    }

    override fun delete(id: FeedIdentity) {
        return commandFeedPersist.delete(id.feedId)
    }

    override fun getFeed(id: FeedIdentity): FeedModel {
        return queryFeedPersist.findById(id.feedId)
    }
}
