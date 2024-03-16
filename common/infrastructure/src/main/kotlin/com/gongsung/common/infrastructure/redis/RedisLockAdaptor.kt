package com.gongsung.common.infrastructure.redis

import com.gongsung.common.infrastructure.LockContext
import com.gongsung.common.infrastructure.LockTemplate
import com.gongsung.common.infrastructure.Options
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisLockAdaptor(
    private val redissonClient: RedissonClient,
) : LockTemplate {

    private val redisLockKeyGenerator = RedisLockKeyGenerator()

    override fun <T> execute(
        lockContext: LockContext,
        options: Options,
        block: () -> T
    ): Result<T> {
        val key : String = redisLockKeyGenerator.generate(lockContext.lockType, lockContext.key)

        val lock = redissonClient.getFairLock(key)

        if (!lock.tryLock(options.waitTimeout, options.releaseTimeout, TimeUnit.SECONDS)) {
            return Result.failure(IllegalStateException("lock 획득 실패."))
        }

        return runCatching(block)
            .also {
                lock.unlock()
            }
    }
}
