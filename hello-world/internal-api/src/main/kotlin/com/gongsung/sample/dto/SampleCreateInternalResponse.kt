package com.gongsung.sample.dto

import com.gongsung.sample.SampleModel

data class SampleCreateInternalResponse(
    override val sampleIdentity: Long,
    override val value: String
) : SampleModel
