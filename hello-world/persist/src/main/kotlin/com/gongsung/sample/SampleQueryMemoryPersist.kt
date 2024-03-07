package com.gongsung.sample

interface SampleQueryMemoryPersist {
    fun find(sampleIdentity: SampleIdentity): SampleModel?
}
