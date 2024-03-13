package com.gongsung.auth

interface Company : CompanyIdentity, CompanyProps {
    companion object {
        fun of(
            identity: CompanyIdentity,
            props: CompanyProps,
        ) = CompanyImpl(
            identity.companyIdentity,
            name = props.name,
            address = props.address,
            website = props.website,
            introduce = props.introduce,
        )
    }
}

interface CompanyIdentity {
    val companyIdentity: Long

    companion object {
        fun of(companyIdentity: Long): CompanyIdentity = CompanyIdentityImpl(companyIdentity)
    }
}

interface CompanyProps {
    var name: String
    var address: String
    var website: String?
    var introduce: String?

    companion object {
        fun of(
            name: String,
            address: String,
            website: String?,
            introduce: String?,
        ): CompanyProps = CompanyPropsImpl(name, address, website, introduce)
    }
}

class CompanyImpl(
    override val companyIdentity: Long,
    override var name: String,
    override var address: String,
    override var website: String?,
    override var introduce: String?,
) : Company

class CompanyIdentityImpl(
    override val companyIdentity: Long,
) : CompanyIdentity

class CompanyPropsImpl(
    override var name: String,
    override var address: String,
    override var website: String?,
    override var introduce: String?,
) : CompanyProps
