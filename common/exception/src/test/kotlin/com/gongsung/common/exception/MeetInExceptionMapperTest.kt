package com.gongsung.common.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MeetInExceptionMapperTest {
    @Test
    fun `If it's a wrapped exception, find the MeetInException and throw it`() {
        val exceptionMapper = ExceptionMapper()
        val exception = TestWrappingException(TestMeetInException())

        val throwable = exceptionMapper.convert(exception)

        assertThat(throwable).isInstanceOf(MeetInException::class.java)
        assertThat(throwable).isInstanceOf(TestMeetInException::class.java)
    }

    @Test
    fun `MeetInException is thrown as is`() {
        val exceptionMapper = ExceptionMapper()
        val exception = TestMeetInException()

        val throwable = exceptionMapper.convert(exception)

        assertThat(throwable).isEqualTo(exception)
    }

    @Test
    fun `If the exception is not a custom-defined exception but is mapped, it will be thrown`() {
        val exceptionMapper = ExceptionMapper(NotImplementedError::class to ::TestMeetInException)
        val exception = NotImplementedError()

        val throwable = exceptionMapper.convert(exception)

        assertThat(throwable).isInstanceOf(TestMeetInException::class.java)
    }

    @Test
    fun `Exceptions that are not found throw a MeetInUnknownException`() {
        val exceptionMapper = ExceptionMapper()
        val unknownException = UnknownException()

        val throwable = exceptionMapper.convert(unknownException)

        assertThat(throwable).isInstanceOf(MeetInUnknownException::class.java)
    }

    private class TestMeetInException(
        override val cause: Throwable? = null,
    ) : MeetInException() {
        override val error: MeetInError
            get() = MeetInCommonError.UNKNOWN
    }

    private class TestWrappingException(
        override val cause: Throwable?,
    ) : RuntimeException()

    private class UnknownException(
        override val cause: Throwable? = null,
    ) : Throwable(cause = cause)
}
