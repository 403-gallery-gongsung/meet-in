package com.gongsung.auth.dto

import com.gongsung.auth.CompanyAccountProps
import com.gongsung.auth.CompanyProps

data class CompanyAccountRequest(
    override val accountProps: AccountPropsRequest,
    override val companyProps: CompanyPropsRequest
) : CompanyAccountProps

data class CompanyPropsRequest(
    override var name: String,
    override var address: String,
    override var website: String?,
    override var introduce: String?

) : CompanyProps
