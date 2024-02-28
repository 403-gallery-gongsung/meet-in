package com.gongsung.feed.entity

import com.gongsung.feed.FeedModel
import com.gongsung.feed.FeedProps
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.LocalTime

@Entity
class Feed(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val feedId: Long = -1,
    override val publisherId: Long = 1,
    override val date: LocalDate = LocalDate.now(),
    override val time: LocalTime = LocalTime.now(),
    override val contents: String = "",
    override var likeCount: Long = 0,
) : FeedModel {

    companion object {
        fun of(feedId: Long, feed: FeedProps) = Feed(
            feedId = feedId,
            publisherId = feed.publisherId,
            date = feed.date,
            time = feed.time,
            contents = feed.contents,
        )
    }
}
