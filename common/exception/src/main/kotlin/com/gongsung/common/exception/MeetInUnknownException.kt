package com.gongsung.common.exception

class MeetInUnknownException(
    throwable: Throwable,
) : MeetInException(throwable) {
    override val error: MeetInError
        get() = MeetInCommonError.UNKNOWN
}
