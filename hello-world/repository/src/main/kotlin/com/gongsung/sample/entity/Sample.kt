package com.gongsung.sample.entity

import com.gongsung.sample.SampleModel
import com.gongsung.sample.SampleProps
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Sample(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = JpaConstants.NOT_YET_SAVED,
    @Column(nullable = false)
    override val value: String = "",
) : SampleModel {
    override val sampleIdentity: Long
        get() = id

    companion object {
        fun ofProps(sampleProps: SampleProps) = Sample(value = sampleProps.value)
    }
}

object JpaConstants {
    const val NOT_YET_SAVED = -1L
}
