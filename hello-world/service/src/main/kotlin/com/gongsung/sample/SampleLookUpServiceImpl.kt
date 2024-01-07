package com.gongsung.sample

import org.springframework.stereotype.Service

@Service
class SampleLookUpServiceImpl(
    private val sampleRepository: SampleRepository,
) : SampleLookUpService {
    override fun find(sampleIdentity: SampleIdentity): SampleModel? {
        return sampleRepository.findByIdOrNull(sampleIdentity)
    }
}
