package com.gongsung.common.exception

abstract class MeetInBadRequestException : MeetInException {
    constructor(message: String? = null) : super(message)
    constructor(throwable: Throwable) : super(throwable)
}
