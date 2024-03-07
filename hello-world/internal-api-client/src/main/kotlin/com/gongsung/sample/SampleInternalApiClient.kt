package com.gongsung.sample

import com.gongsung.sample.mutation.SampleService

class SampleInternalApiClient(

): SampleService {
    override fun create(sampleProps: SampleProps): SampleModel {
        throw NotImplementedError()
    }
}
