package com.gongsung.common.lock

import io.github.oshai.kotlinlogging.KotlinLogging
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class LockService(
    private val redissonClient: RedissonClient,
) {

    private val log = KotlinLogging.logger { }

    companion object {
        const val LOCK_LEASE_TIME = 3L
        const val LOCK_WAIT_TIME = 3L
    }

    fun <T> executeWithLock(key: String, supplier: () -> T): T {
        val lock: RLock = redissonClient.getLock(key)

        try {
            val available: Boolean = lock.tryLock(LOCK_WAIT_TIME, LOCK_LEASE_TIME, TimeUnit.SECONDS)
            if (!available) {
                throw IllegalStateException("lock 획득 실패.")
            }

            return supplier.invoke()
        } catch (e: Exception) {
            // thread interrupt or lock 획득 실패
            log.warn { "tryLock 실패." }
            throw e
        } finally {
            // lock 해제
            lock.unlock()
        }
    }
}
