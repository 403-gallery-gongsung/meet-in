package com.gongsung.sample.lookup

import com.gongsung.sample.SampleIdentity
import com.gongsung.sample.SampleModel
import com.gongsung.sample.SampleMySqlRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SampleLookUpServiceImpl(
    private val sampleMySqlRepository: SampleMySqlRepository,
) : SampleLookUpService {
    override fun find(sampleIdentity: SampleIdentity): SampleModel? {
        return sampleMySqlRepository.findByIdOrNull(sampleIdentity.sampleIdentity)
    }
}
