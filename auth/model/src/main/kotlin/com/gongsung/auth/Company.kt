package com.gongsung.auth

interface Company : CompanyIdentity, CompanyProps {
    companion object {
        fun of(identity: CompanyIdentity, props: CompanyProps) = CompanyImpl(
            identity.companyIdentity,
            props.loginId,
            props.password,
            props.name,
            props.website,
            props.introduce,
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
    val loginId: String
    var password: String
    var name: String
    var website: String
    var introduce: String

    companion object {
        fun of(
            loginId: String,
            password: String,
            name: String,
            website: String,
            introduce: String
        ): CompanyProps = CompanyPropsImpl(loginId, password, name, website, introduce)
    }
}

class CompanyImpl(
    override val companyIdentity: Long,
    override val loginId: String,
    override var password: String,
    override var name: String,
    override var website: String,
    override var introduce: String
) : Company

class CompanyIdentityImpl(
    override val companyIdentity: Long
) : CompanyIdentity

class CompanyPropsImpl(
    override val loginId: String,
    override var password: String,
    override var name: String,
    override var website: String,
    override var introduce: String
) : CompanyProps