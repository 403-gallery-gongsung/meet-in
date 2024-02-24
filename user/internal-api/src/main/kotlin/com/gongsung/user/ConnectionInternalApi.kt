package com.gongsung.user

import com.gongsung.connection.command.ConnectionRequestCommandUseCase
import com.gongsung.connection.query.ConnectionRequestQueryUseCase
import com.gongsung.user.dto.ConnectionRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal/v1/connection")
class ConnectionInternalApi(
    private val connectionRequestCommandUseCase: ConnectionRequestCommandUseCase,
    private val connectionRequestQueryUseCase: ConnectionRequestQueryUseCase
) {

    @GetMapping("/{userId}")
    fun getAllConnectionByUserId(
        @PathVariable("userId") userId: Long
    ): ResponseEntity<List<ConnectionRequest>> {
        return connectionRequestQueryUseCase.getAllConnectionByUserId(UserIdentity.of(userId))
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping
    fun getConnectionRequest(
        @RequestParam("fromUserId") fromUserId: Long,
        @RequestParam("toUserId") toUserId: Long
    ): ResponseEntity<ConnectionRequest> {
        return connectionRequestQueryUseCase.getByFromUserIdAndToUserId(
            UserIdentity.of(fromUserId),
            UserIdentity.of(toUserId),
        )?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.internalServerError().build()
    }

    @PostMapping
    fun createConnectionRequest(
        @RequestBody connectionRequest: ConnectionRequestDto
    ): ResponseEntity<ConnectionRequest> {
        return connectionRequestCommandUseCase.create(
            fromUserIdentity = UserIdentity.of(connectionRequest.fromUserId),
            toUserIdentity = UserIdentity.of(connectionRequest.toUserId),
        ).let { ResponseEntity.ok(it) }
    }

    @PutMapping
    fun updateConnectionRequest(
        @RequestBody connectionRequest: ConnectionRequestDto
    ): ResponseEntity<ConnectionRequest> {
        return connectionRequestCommandUseCase.update(
            connectionRequest,
        ).let { ResponseEntity.ok(it) }
    }
}
