package com.gongsung.auth.entity

import com.gongsung.auth.Job
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.validation.annotation.Validated

@Entity
@Validated
@Table(name = "JOB")
class JobEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = JpaConstant.NOT_YET,
    @Column(nullable = false, length = 20)
    override var title: String = "",
    @Column(nullable = false)
    override var description: String = "",
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    val company: CompanyEntity? = null,
) : Job {
    override val jobIdentity: Long
        get() = id
}
