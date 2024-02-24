package com.gongsung.user.domain.dto

import com.gongsung.user.domain.enums.Response

class BaseResponse<T> (
    val code: String = Response.SUCCESS.name,
    val data: T? = null,
    val message: String = Response.SUCCESS.message
)