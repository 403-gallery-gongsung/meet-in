package com.gongsung.auth.dto

import com.gongsung.auth.CompanyProps

class CompanyRequest(
    override val loginId: String,
    override var password: String,
    override var name: String,
    override var website: String,
    override var introduce: String,
) : CompanyProps