package com.gongsung.feed

import org.springframework.stereotype.Service

@Service
class FeedService(
    private val commandFeedPersist: CommandFeedPersist,
    private val queryFeedPersist: QueryFeedPersist,
    private val commandLikePersist: CommandLikePersist,
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

    override fun like(
        feedId: Long,
        userId: Long,
    ) {
        // TODO 동시성 이슈 해결해야함
        commandFeedPersist.increaseLikes(feedId)

        commandLikePersist.create(feedId, userId)
    }

    override fun dislike(
        feedId: Long,
        userId: Long,
    ) {
        commandFeedPersist.decreaseLikes(feedId)

        commandLikePersist.delete(feedId, userId)
    }

    override fun getFeed(id: FeedIdentity): FeedModel {
        return queryFeedPersist.findById(id.feedId)
    }
}
