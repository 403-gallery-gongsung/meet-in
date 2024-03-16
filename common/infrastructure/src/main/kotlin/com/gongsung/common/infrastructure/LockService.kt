package com.gongsung.common.infrastructure

import org.springframework.stereotype.Component

private lateinit var container: LockTransactionContainer

fun <T> withLock(
    context: LockContext,
    block: LockScope<T>.() -> T
): Result<T> = container.eval(context.newLockScope(), block)

@Component
internal open class LockTransactionContainer(
    private val lockTemplate: LockTemplate
) {

    open fun <T> eval(
        scope: LockScope<T>,
        block: LockScope<T>.() -> T
    ): Result<T> =
        scope.run {
            lockTemplate.execute(lockContext, options){ block() }
        }
}

@Component
private class LockService private constructor(
    lockTransactionContainer: LockTransactionContainer
) {
    init {
        container = lockTransactionContainer
    }
}
