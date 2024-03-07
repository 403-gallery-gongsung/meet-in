package com.gongsung.sample

interface SampleIdentity {
    val sampleIdentity: Long

    companion object {
        fun of(id: Long) = SimpleSampleIdentity(sampleIdentity = id)
    }
}

interface SampleProps {
    val value: String
}

interface SampleModel :
    SampleIdentity,
    SampleProps

data class SimpleSampleIdentity(
    override val sampleIdentity: Long,
) : SampleIdentity
