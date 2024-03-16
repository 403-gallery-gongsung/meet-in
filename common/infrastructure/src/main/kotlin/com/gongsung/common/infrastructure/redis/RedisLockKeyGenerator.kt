package com.gongsung.common.infrastructure.redis

class RedisLockKeyGenerator {

    fun generate(lockType: LockType, key: String): String {
        return lockType.keyPrefix + key
    }
}
