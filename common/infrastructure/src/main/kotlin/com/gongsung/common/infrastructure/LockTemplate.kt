package com.gongsung.common.infrastructure

interface LockTemplate {
    fun <T> execute(lockContext: LockContext, options: Options, block: () -> T): Result<T>
}
