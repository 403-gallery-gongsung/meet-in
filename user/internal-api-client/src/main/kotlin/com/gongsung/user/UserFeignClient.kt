package com.gongsung.user

import com.gongsung.auth.User
import com.gongsung.auth.UserProps
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@FeignClient(name = "user", url = "\${api-client.user}/internal/v1/users")
interface UserFeignClient {
    @PostMapping
    fun createUser(@RequestBody userProps: UserProps): ResponseEntity<User>
}
