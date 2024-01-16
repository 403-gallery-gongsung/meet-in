package com.gongsung.dto

import com.gonsung.company.CompanyProps

data class CompanyDTO(
    override val name: String,
    override val address: String
) : CompanyProps
