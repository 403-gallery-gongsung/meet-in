package com.gongsung.user

import com.gongsung.follow.command.FollowCommandUseCase
import com.gongsung.follow.query.FollowQueryUseCase
import com.gongsung.user.dto.FollowToCompanyRequestDto
import com.gongsung.user.dto.FollowToUserRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/internal/v1/follow")
class FollowInternalApi(
    private val followQueryUseCase: FollowQueryUseCase,
    private val followCommandUseCase: FollowCommandUseCase
) {

    @PostMapping("/user")
    fun followToUser(
        @RequestBody followToUserRequestDto: FollowToUserRequestDto
    ): Follow {
        return followCommandUseCase.followToUser(followToUserRequestDto)
    }

    @PostMapping("/company")
    fun followToCompany(
        @RequestBody followToCompanyRequestDto: FollowToCompanyRequestDto
    ): Follow {
        return followCommandUseCase.followToCompany(followToCompanyRequestDto)
    }

    @DeleteMapping("/user")
    fun unFollowToUser(
        @RequestBody followToUserRequestDto: FollowToUserRequestDto
    ): Boolean {
        return followCommandUseCase.unFollowToUser(followToUserRequestDto)
    }

    @DeleteMapping("/company")
    fun unFollowToCompany(
        @RequestBody followToCompanyRequestDto: FollowToCompanyRequestDto
    ): Boolean {
        return followCommandUseCase.unFollowToCompany(followToCompanyRequestDto)
    }

    @GetMapping("/user")
    fun getByFromUserIdAndToUserId(
        @RequestParam("fromUserId") fromUserId: Long,
        @RequestParam("toUserId") toUserId: Long
    ): ResponseEntity<Follow> {
        return followQueryUseCase.getByFromUserIdAndToUserId(
            UserIdentity.of(
                fromUserId,
            ),
            UserIdentity.of(
                toUserId,
            ),
        )?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/company")
    fun getByFromUserIdAndToCompanyId(
        @RequestParam("fromUserId") fromUserId: Long,
        @RequestParam("toCompanyId") toCompanyId: Long
    ): ResponseEntity<Follow> {
        return followQueryUseCase.getByFromUserIdAndToCompanyId(
            UserIdentity.of(
                fromUserId,
            ),
            toCompanyId,
        )?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }
}
