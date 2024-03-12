package com.gongsung.common.infrastructure

import com.gongsung.common.infrastructure.redis.LockType

data class Options(
    val waitTimeout: Long,
    val releaseTimeout: Long,
)

fun <T> LockContext.newLockScope() = object : LockScope<T> {
    override val lockContext: LockContext = this@newLockScope
    override val options: Options = Options(3, 2) /* 몇초가 최적일지는 테스트해보며 조절 */
}

interface LockScope<T> {
    val lockContext: LockContext
    val options: Options
}

data class LockContext(
    val lockType: LockType,
    val key: String,
)