package com.gongsung.auth.command

import com.gongsung.auth.AccountProps
import com.gongsung.auth.CompanyAccount
import com.gongsung.auth.CompanyProps
import com.gongsung.auth.UserAccount
import com.gongsung.auth.UserProps

interface SignUpUseCase {
    fun signUpUser(accountProps: AccountProps, userProps: UserProps): UserAccount

    fun signUpCompany(accountProps: AccountProps, companyProps: CompanyProps): CompanyAccount
}