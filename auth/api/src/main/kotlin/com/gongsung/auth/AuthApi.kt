package com.gongsung.auth

import com.gongsung.auth.command.SignInUseCase
import com.gongsung.auth.command.SignUpUseCase
import com.gongsung.auth.dto.AccountRequest
import com.gongsung.auth.dto.CompanyAccountRequest
import com.gongsung.auth.dto.UserAccountRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthApi(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) {
    @PostMapping("/signin")
    fun signIn(
        @RequestBody @Valid accountRequest: AccountRequest,
    ): ResponseEntity<TokenInfo> =
        accountRequest.let(signInUseCase::signIn)
            .let { ResponseEntity.ok(it) }

    @PostMapping("/signup-user")
    fun signUpUser(
        @RequestBody @Valid userAccountRequest: UserAccountRequest
    ): ResponseEntity<UserAccount> = signUpUseCase.signUpUser(
        accountProps = userAccountRequest.accountProps,
        userProps = userAccountRequest.userProps,
    ).let { ResponseEntity.ok(it) }


    @PostMapping("/signup-company")
    fun signUpCompany(
        @RequestBody @Valid companyAccountRequest: CompanyAccountRequest
    ): ResponseEntity<CompanyAccount> = signUpUseCase.signUpCompany(
        accountProps = companyAccountRequest.accountProps,
        companyProps = companyAccountRequest.companyProps,
    ).let { ResponseEntity.ok(it) }

}
