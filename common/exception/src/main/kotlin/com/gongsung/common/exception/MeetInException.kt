package com.gongsung.common.exception

abstract class MeetInException : RuntimeException {
    constructor(message: String? = null) : super(message)
    constructor(throwable: Throwable) : super(throwable)

    abstract val error: MeetInError

    open fun getMessageArguments(): Array<Any> = emptyArray()
}
