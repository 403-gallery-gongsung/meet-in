package com.gongsung.feed

import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalTime

@RestController
@RequestMapping("/internal/v1/feeds")
class FeedController(
    private val commandFeedUseCase: CommandFeedUseCase,
    private val getFeedUseCase: GetFeedUseCase,
) {

    @PostMapping
    fun create(@RequestBody request: CreateFeedRequest): FeedModel {
        return commandFeedUseCase.create(request)
    }

    @PutMapping
    fun update(@RequestBody request: UpdateFeedRequest): FeedModel {
        return commandFeedUseCase.update(request.feedId, request)
    }

    @DeleteMapping("/{feedId}")
    fun delete(
        @PathVariable feedId: Long,
    ) {
        commandFeedUseCase.delete(FeedIdentity.of(feedId))
    }

    @GetMapping("/{feedId}")
    fun getFeed(
        @PathVariable feedId: Long,
    ): FeedModel {
        return feedId
            .let(FeedIdentity.Companion::of)
            .run(getFeedUseCase::getFeed)
    }

    @PostMapping("/{feedId}/like")
    fun like(
        @PathVariable feedId: Long,
        @RequestBody request: FeedLikeRequest,
    ) {
        commandFeedUseCase.like(feedId, request.userId)
    }

    @PostMapping("/{feedId}/dislike")
    fun dislike(
        @PathVariable feedId: Long,
        @RequestBody request: FeedLikeRequest,
    ) {
        commandFeedUseCase.dislike(feedId, request.userId)
    }
}

data class CreateFeedRequest(
    override val publisherId: Long,
    override val contents: String,
) : FeedProps {

    override val date: LocalDate = LocalDate.now()

    override val time: LocalTime = LocalTime.now()
}

data class UpdateFeedRequest(
    val feedId: Long,
    override val publisherId: Long,
    override val contents: String,
) : FeedProps {

    override val date: LocalDate = LocalDate.now()

    override val time: LocalTime = LocalTime.now()
}

data class FeedLikeRequest(
    val userId: Long,
)
