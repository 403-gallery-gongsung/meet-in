package com.gongsung.sample

interface SampleQueryMysqlPersist {
    fun find(sampleIdentity: SampleIdentity): SampleModel?
}
