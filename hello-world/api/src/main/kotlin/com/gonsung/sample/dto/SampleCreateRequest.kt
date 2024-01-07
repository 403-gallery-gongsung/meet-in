package com.gonsung.sample.dto

import com.gongsung.sample.SampleProps

data class SampleCreateRequest(
    override val value: String,
) : SampleProps
