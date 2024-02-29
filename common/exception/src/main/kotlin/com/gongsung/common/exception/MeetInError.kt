package com.gongsung.common.exception

interface MeetInError {
    val name: String
    val code: String
    val messageNamespace: String
    val messageKey: String
        get() = "$messageNamespace.$code"
    val level: MeetInErrorLevel
}
