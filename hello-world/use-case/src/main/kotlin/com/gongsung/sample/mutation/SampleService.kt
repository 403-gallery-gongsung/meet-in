package com.gongsung.sample.mutation

import com.gongsung.sample.SampleModel
import com.gongsung.sample.SampleProps

interface SampleService {
    fun create(sampleProps: SampleProps): SampleModel
}
