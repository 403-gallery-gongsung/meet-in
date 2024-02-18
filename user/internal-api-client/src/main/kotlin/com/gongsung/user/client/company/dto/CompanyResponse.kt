package com.gongsung.user.client.company.dto

import com.gongsung.company.Company

data class CompanyResponse(
    override val id: Long,
    override val name: String,
    override val address: String
) : Company
