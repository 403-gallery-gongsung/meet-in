package com.gongsung.company.entity

import com.gonsung.company.Company
import com.gonsung.company.CompanyProps
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "company")
data class CompanyEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = JpaConstants.NOT_YET_SAVED,
    override val address: String = "",
    override val name: String = "",
) : Company {

    companion object {
        fun ofProps(companyProps: CompanyProps) = CompanyEntity(
            address = companyProps.address,
            name = companyProps.name,
        )
    }
}

object JpaConstants {
    const val NOT_YET_SAVED = -1L
}
