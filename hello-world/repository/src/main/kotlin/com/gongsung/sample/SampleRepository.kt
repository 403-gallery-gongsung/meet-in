package com.gongsung.sample

import com.gongsung.sample.entity.Sample

interface SampleRepository {
    fun findByIdOrNull(sampleIdentity: SampleIdentity): SampleModel?

    fun save(sample: Sample): SampleModel
}
