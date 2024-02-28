package com.gongsung.feed

interface CommandLikePersist {

    fun create(feedId: Long, userId: Long)

    fun delete(feedId: Long, userId: Long)
}