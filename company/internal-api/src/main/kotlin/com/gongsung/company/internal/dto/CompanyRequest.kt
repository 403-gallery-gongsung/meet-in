package com.gongsung.company.internal.dto

import com.gonsung.company.CompanyProps

class CompanyRequest(
    override val name: String,
    override val address: String
) : CompanyProps {
}
