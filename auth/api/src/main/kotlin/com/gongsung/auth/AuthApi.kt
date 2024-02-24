package com.gongsung.auth

import com.gongsung.auth.command.CommandAuthUseCase
import com.gongsung.auth.command.CommandCompanyUseCase
import com.gongsung.auth.command.CommandUserUseCase
import com.gongsung.auth.dto.AuthRequest
import com.gongsung.auth.dto.CompanyRequest
import com.gongsung.auth.dto.UserRequest
import com.gongsung.auth.query.QueryCompanyUseCase
import com.gongsung.auth.query.QueryUserUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthApi(
    private val queryUserUseCase: QueryUserUseCase,
    private val queryCompanyUseCase: QueryCompanyUseCase,
    private val commandUserUseCase: CommandUserUseCase,
    private val commandCompanyUseCase: CommandCompanyUseCase,
    private val commandUseCase: CommandAuthUseCase
) {
    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid authRequest: AuthRequest): ResponseEntity<TokenInfo> =
//        Todo : 403 Forbidden. -> Spring Security 구성 오류 (보호된 end-point에 대한 권한이 설정되지 않았거나 잘못 설정된 경우 인가 오류가 발생할 수 있음.
        authRequest.let(commandUseCase::signIn)
            .let { ResponseEntity.ok(it) }


    @PostMapping("/createUser")
    fun createUser(@RequestBody @Valid userRequest: UserRequest): ResponseEntity<User> =
        userRequest.let(commandUserUseCase::createUser)
            .let { ResponseEntity.ok(it) }

    @PostMapping("/createCompany")
    fun createCompany(@RequestBody @Valid companyRequest: CompanyRequest): ResponseEntity<Company> =
        companyRequest.let(commandCompanyUseCase::createCompany)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable("id") userId: Long): ResponseEntity<User> =
        userId.let(UserIdentity::of)
            .run(queryUserUseCase::getUserById)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/company/{id}")
    fun getCompany(@PathVariable("id") companyId: Long): ResponseEntity<Company> =
        companyId.let(CompanyIdentity::of)
            .run(queryCompanyUseCase::getCompanyById)
            .let { ResponseEntity.ok(it) }
}