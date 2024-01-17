package com.gongsung.user.controller

import com.gongsung.common.authority.TokenInfo
import com.gongsung.user.domain.dto.BaseResponse
import com.gongsung.user.domain.dto.LoginDto
import com.gongsung.user.domain.dto.UserDto
import com.gongsung.user.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid userDto: UserDto): BaseResponse<Unit> {
        val message: String = userService.signUp(userDto)
        return BaseResponse(message = message)
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = userService.signIn(loginDto)

        return BaseResponse(data = tokenInfo)
    }
}