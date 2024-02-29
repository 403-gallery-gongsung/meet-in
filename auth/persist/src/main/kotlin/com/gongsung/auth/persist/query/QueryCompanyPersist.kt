package com.gongsung.auth.persist.query

import com.gongsung.auth.Company

interface QueryCompanyPersist {
    fun getCompanyById(id: Long): Company
}
