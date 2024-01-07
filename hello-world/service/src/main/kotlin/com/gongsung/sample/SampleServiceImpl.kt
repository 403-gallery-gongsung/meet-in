package com.gongsung.sample

import com.gongsung.sample.entity.Sample
import org.springframework.stereotype.Service

@Service
class SampleServiceImpl(
    private val sampleRepository: SampleRepository,
) : SampleService {
    override fun create(sampleProps: SampleProps): SampleModel {
        return sampleProps
            .let(Sample::ofProps)
            .run(sampleRepository::save)
    }
}
