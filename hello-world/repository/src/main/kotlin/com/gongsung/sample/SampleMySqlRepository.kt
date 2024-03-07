package com.gongsung.sample

import com.gongsung.sample.entity.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleMySqlRepository :
    SampleCommandMysqlPersist,
    SampleQueryMysqlPersist,
    JpaRepository<Sample, Long> {
    override fun update(sampleModel: SampleModel): SampleModel =
        sampleModel
            .let(Sample::ofProps)
            .run(::save)
}
