package com.gongsung.common.exception

enum class MeetInCommonError(
    override val code: String,
    override val messageNamespace: String = "com.gongsung.common.exception",
    override val level: MeetInErrorLevel = MeetInErrorLevel.INFO,
) : MeetInError {
    BAD_REQUEST(code = "COMMON_400_000", level = MeetInErrorLevel.INFO),

    UNKNOWN(code = "COMMON_500_000", level = MeetInErrorLevel.ERROR),
}
