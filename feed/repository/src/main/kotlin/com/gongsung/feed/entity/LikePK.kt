package com.gongsung.feed.entity

import java.io.Serializable

data class LikePK(
    val userId: Long,
    val feedId: Long,
) : Serializable