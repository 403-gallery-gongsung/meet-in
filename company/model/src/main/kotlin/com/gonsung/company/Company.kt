package com.gonsung.company

interface Company : CompanyProps, CompanyIdentity {
    companion object {
        fun of(companyIdentity: CompanyIdentity, props: CompanyProps): Company =
            CompanyImpl(
                companyIdentity.companyIdentity,
                props.name,
                props.address,
            )
    }
}

interface CompanyProps {
    val name: String
    val address: String

    companion object {
        fun of(
            name: String,
            address: String,
        ): CompanyProps = CompanyPropsImpl(name, address)
    }
}

interface CompanyIdentity {
    val companyIdentity: Long

    companion object {
        fun of(companyIdentity: Long): CompanyIdentity = CompanyIdentityImpl(companyIdentity)
    }
}

data class CompanyPropsImpl(
    override val name: String,
    override val address: String,
) : CompanyProps

data class CompanyIdentityImpl(
    override val companyIdentity: Long,
) : CompanyIdentity

data class CompanyImpl(
    override val companyIdentity: Long,
    override val name: String,
    override val address: String,
) : Company
