package com.gongsung.common.infrastructure.redis

enum class LockType(
    val keyPrefix: String,
) {

    FEED_LIKE("FEED_LIKE:")
    ;
}