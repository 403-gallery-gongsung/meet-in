package com.gongsung.sample

interface SampleLookUpService {
    fun find(sampleIdentity: SampleIdentity): SampleModel?

    fun get(sampleIdentity: SampleIdentity): SampleModel =
        find(sampleIdentity = sampleIdentity)
            ?: throw IllegalArgumentException()
}
