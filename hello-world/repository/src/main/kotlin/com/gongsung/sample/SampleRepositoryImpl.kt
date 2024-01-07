package com.gongsung.sample

import com.gongsung.sample.entity.Sample
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class SampleRepositoryImpl(
    private val sampleMySqlRepository: SampleMySqlRepository,
) : SampleRepository {
    override fun findByIdOrNull(sampleIdentity: SampleIdentity): SampleModel? {
        return sampleMySqlRepository.findByIdOrNull(sampleIdentity.sampleIdentity)
    }

    override fun save(sample: Sample): SampleModel {
        return sampleMySqlRepository.save(sample)
    }
}
