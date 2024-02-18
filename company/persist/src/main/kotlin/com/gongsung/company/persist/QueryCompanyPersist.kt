package com.gongsung.company.persist

import com.gonsung.company.Company

interface QueryCompanyPersist {
    fun getCompanyById(id: Long): Company
}
