package com.gonsung.company

interface Company : CompanyProps, CompanyIdentity
interface CompanyProps {
    val name: String
    val address: String
}

interface CompanyIdentity {
    val companyIdentity: Long
}
