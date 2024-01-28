package com.gongsung.sample

interface SampleCommandMysqlPersist {
    fun create(sampleProps: SampleProps): SampleModel

    fun update(sampleModel: SampleModel): SampleModel
}
