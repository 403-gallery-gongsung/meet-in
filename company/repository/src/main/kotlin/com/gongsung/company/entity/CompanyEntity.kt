package com.gongsung.company.entity

import com.gonsung.company.Company
import com.gonsung.company.CompanyProps
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class CompanyEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    override val address: String = "",
    override val name: String = "",
) : Company {

    companion object {
        fun ofProps(companyProps: CompanyProps) = CompanyEntity(
            address = companyProps.address,
            name = companyProps.name,
        )
    }

    override val companyIdentity: Long
        get() = id ?: throw Exception("Company Entity is not saved yet")
}
