package com.gongsung.user

import com.gongsung.user.command.CommandUserUseCase
import com.gongsung.user.dto.UserInternalRequest
import com.gongsung.user.query.QueryUserUseCase
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping


@FeignClient(name = "user", url = "\${api-client.user}/internal/v1/users")
interface UserInternalApiClient : QueryUserUseCase, CommandUserUseCase {
    @PostMapping
    fun createUser(userInternalRequest: UserInternalRequest): ResponseEntity<User>
}
