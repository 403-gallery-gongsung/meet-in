package com.gongsung.sample.lookup

import com.gongsung.sample.SampleIdentity
import com.gongsung.sample.SampleModel

interface SampleLookUpService {
    fun find(sampleIdentity: SampleIdentity): SampleModel?

    fun get(sampleIdentity: SampleIdentity): SampleModel =
        find(sampleIdentity = sampleIdentity)
            ?: throw IllegalArgumentException()
}
