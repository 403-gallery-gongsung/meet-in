package com.gongsung.sample.dto

import com.gongsung.sample.SampleProps

data class SampleCreateInternalRequest(
    override val value: String
) : SampleProps
