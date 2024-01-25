package com.gongsung.user.service

import com.gongsung.user.domain.dto.CustomUser
import com.gongsung.user.domain.entity.UserEntity
import com.gongsung.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByLoginId(username)?.let { createUserDetails(it) }
            ?: throw UsernameNotFoundException("존재하지 않는 유저입니다")


    private fun createUserDetails(userEntity: UserEntity): UserDetails =
        CustomUser(
            userEntity.id!!,
            userEntity.loginId,
            passwordEncoder.encode(userEntity.password),
            userEntity.userRole!!.map { SimpleGrantedAuthority("ROLE_${it.role}") },
        )
}