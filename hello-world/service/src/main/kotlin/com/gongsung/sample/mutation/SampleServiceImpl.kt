package com.gongsung.sample.mutation

import com.gongsung.sample.SampleModel
import com.gongsung.sample.SampleMySqlRepository
import com.gongsung.sample.SampleProps
import com.gongsung.sample.entity.Sample
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class SampleServiceImpl(
    private val sampleMySqlRepository: SampleMySqlRepository,
) : SampleService {
    @Transactional
    override fun create(sampleProps: SampleProps): SampleModel {
        return sampleProps
            .let(Sample::ofProps)
            .run(sampleMySqlRepository::save)
    }
}
