package com.gongsung.user.service


import com.gongsung.common.authority.JwtTokenProvider
import com.gongsung.common.authority.TokenInfo
import com.gongsung.common.exception.InvalidInputException
import com.gongsung.user.domain.dto.LoginDto
import com.gongsung.user.domain.dto.UserDto
import com.gongsung.user.domain.dto.UserDtoResponse
import com.gongsung.user.domain.entity.UserEntity
import com.gongsung.user.domain.entity.UserRole
import com.gongsung.user.domain.enums.Role
import com.gongsung.user.repository.UserRoleRepository
import com.gongsung.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val userRoleRepository: UserRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun signUp( userDto: UserDto): String {
        var user: UserEntity? = userRepository.findByLoginId(userDto.loginId)
        if (user != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        user = userDto.toEntity()
        userRepository.save(user)

        val role = UserRole(null, Role.USER, user)
        userRoleRepository.save(role)
        return "회원 가입이 완료되었습니다."
    }


    fun signIn(loginDto: LoginDto): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }

    fun searchMyInfo(id: Long): UserDtoResponse {
        val user: UserEntity =
            userRepository.findByIdOrNull(id) ?: throw InvalidInputException("id", "회원(${id})가 존재하지 않는 유저입니다")

        return user.toDto()
    }
}