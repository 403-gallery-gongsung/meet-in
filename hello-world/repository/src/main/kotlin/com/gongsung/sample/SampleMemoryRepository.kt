package com.gongsung.sample

class SampleMemoryRepository :
    SampleCommandMemoryPersist,
    SampleQueryMemoryPersist {
    companion object {
        val map = mutableMapOf<String, String>()
    }

    override fun put(sampleModel: SampleModel): SampleModel {
        TODO("Not yet implemented")
    }

    override fun find(sampleIdentity: SampleIdentity): SampleModel? {
        TODO("Not yet implemented")
    }
}