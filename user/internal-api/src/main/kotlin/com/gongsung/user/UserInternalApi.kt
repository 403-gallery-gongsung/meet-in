package com.gongsung.user

import com.gongsung.user.command.CommandUseCase
import com.gongsung.user.dto.UserInternalRequest
import com.gongsung.user.query.QueryUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/internal/user")
class UserInternalApi(
    private val commandUseCase: CommandUseCase,
    private val queryUseCase: QueryUseCase
) {
    @GetMapping("{id}")
    fun getUser(@PathVariable("id") userId: Long): ResponseEntity<User> {
        return userId.let(UserIdentity::of)
            .let(queryUseCase::getById)
            .let { ResponseEntity.ok(it) }
    }

    @PostMapping
    fun createUser(@RequestBody userRequest: UserInternalRequest): ResponseEntity<User> {
        return userRequest.let(commandUseCase::create)
            .let { ResponseEntity.ok(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") userId: Long): ResponseEntity<Boolean> {
        return userId.let(UserIdentity::of)
            .run(commandUseCase::delete)
            .let { ResponseEntity.ok(it) }
    }

    @PutMapping("{id}")
    fun updateUser(
        @PathVariable("id") userId: Long, @RequestBody userRequest: UserInternalRequest
    ): ResponseEntity<User> {
        return User.of(identity = UserIdentity.of(userId), userRequest)
            .let {
                commandUseCase::update
                ResponseEntity.ok(it)
            }
    }
}