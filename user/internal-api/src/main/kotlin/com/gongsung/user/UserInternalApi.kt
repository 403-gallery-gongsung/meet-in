package com.gongsung.user

import com.gongsung.user.command.CommandUserUseCase
import com.gongsung.user.dto.UserInternalRequest
import com.gongsung.user.query.QueryUserUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal/v1/users")
class UserInternalApi(
    private val commandUseCase: CommandUserUseCase,
    private val queryUseCase: QueryUserUseCase
) {
    @GetMapping("{id}")
    fun getUser(@PathVariable("id") userId: Long): ResponseEntity<User> {
        return userId.let(UserIdentity::of)
            .run(queryUseCase::getUserById)
            .let { ResponseEntity.ok(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") userId: Long): ResponseEntity<Boolean> {
        return userId.let(UserIdentity::of)
            .run(commandUseCase::deleteUser)
            .let { ResponseEntity.ok(it) }
    }

    @PutMapping("{id}")
    fun updateUser(
        @PathVariable("id") userId: Long, @RequestBody userRequest: UserInternalRequest
    ): ResponseEntity<User> {
        return User.of(identity = UserIdentity.of(userId), userRequest)
            .run(commandUseCase::updateUser)
            .let { ResponseEntity.ok(it) }
    }
}