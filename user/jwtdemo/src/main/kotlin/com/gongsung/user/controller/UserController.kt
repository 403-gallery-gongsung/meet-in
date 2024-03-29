package com.gongsung.user.controller

import com.gongsung.common.authority.TokenInfo
import com.gongsung.user.domain.dto.BaseResponse
import com.gongsung.user.domain.dto.CustomUser
import com.gongsung.user.domain.dto.LoginDto
import com.gongsung.user.domain.dto.UserDtoRequest
import com.gongsung.user.domain.dto.UserDtoResponse
import com.gongsung.user.service.UserService
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {
    @PostMapping("/signup")
    fun signUp(
        @RequestBody @Valid
        userDto: UserDtoRequest,
    ): BaseResponse<Unit> {
        val message: String = userService.signUp(userDto)
        return BaseResponse(message = message)
    }

    @PostMapping("/signin")
    fun signIn(
        @RequestBody @Valid
        loginDto: LoginDto,
    ): BaseResponse<TokenInfo> {
        val tokenInfo = userService.signIn(loginDto)

        return BaseResponse(data = tokenInfo)
    }

    @GetMapping("/info")
    fun searchMyInfo(@PathVariable id: Long): BaseResponse<UserDtoResponse> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val response = userService.searchMyInfo(userId)

        return BaseResponse(data = response)
    }

    @PutMapping("/info")
    fun updateMyInfo(
        @RequestBody @Valid
        userDto: UserDtoRequest,
    ): BaseResponse<Unit> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        userDto.id = userId

        val result: String = userService.updateMyInfo(userDto)
        return BaseResponse(message = result)
    }
}
