package com.gongsung.feed

import java.time.LocalDate
import java.time.LocalTime

interface FeedModel : FeedIdentity, FeedProps, FeedLike

interface FeedIdentity {
    val feedId: Long

    companion object {
        fun of(id: Long) = FeedIdentityImpl(feedId = id)
    }
}

interface FeedProps : FeedDetail {
    val publisherId: Long
}

interface PublisherInfo {
    val publisherId: Long
    val publisherName: String
}

interface FeedDetail {
    val date: LocalDate
    val time: LocalTime
    val contents: String
}

data class FeedIdentityImpl(
    override val feedId: Long,
) : FeedIdentity
