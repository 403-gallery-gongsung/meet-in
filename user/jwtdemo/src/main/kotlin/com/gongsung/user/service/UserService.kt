package com.gongsung.user.service


import com.gongsung.common.authority.JwtTokenProvider
import com.gongsung.common.authority.TokenInfo
import com.gongsung.common.exception.InvalidInputException
import com.gongsung.user.domain.dto.LoginDto
import com.gongsung.user.domain.dto.UserDto
import com.gongsung.user.domain.entity.UserEntity
import com.gongsung.user.domain.entity.UserRole
import com.gongsung.user.domain.enums.Role
import com.gongsung.user.repository.UserRepository
import jakarta.transaction.TransactionScoped
import jakarta.transaction.Transactional
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) {

    fun signUp(userDto: UserDto): String {
        var user: UserEntity? = userRepository.findByLoginId(userDto.loginId)
        if (user != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        user = userDto.toEntity()

        userRepository.save(user)

        val role: UserRole = UserRole(null, Role.USER, user)

        return "회원 가입이 완료되었습니다."
    }


    fun signIn(loginDto: LoginDto): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}