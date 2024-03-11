package com.gongsung.auth

interface CompanyAccount : AccountIdentity, CompanyAccountProps {
    companion object {
        fun of(
            identity: AccountIdentity,
            props: CompanyAccountProps
        ) = CompanyAccountImpl(
            identity.accountIdentity,
            accountProps = props.accountProps,
            companyProps = props.companyProps,
        )
    }
}

interface CompanyAccountProps {
    val accountProps: AccountProps
    val companyProps: CompanyProps

    companion object {
        fun of(
            accountProps: AccountProps,
            companyProps: CompanyProps
        ) = CompanyAccountPropsImpl(
            accountProps,
            companyProps,
        )
    }
}

class CompanyAccountImpl(
    override val accountIdentity: Long,
    override val accountProps: AccountProps,
    override val companyProps: CompanyProps

) : CompanyAccount {

}

class CompanyAccountPropsImpl(
    override val accountProps: AccountProps,
    override val companyProps: CompanyProps

) : CompanyAccountProps

