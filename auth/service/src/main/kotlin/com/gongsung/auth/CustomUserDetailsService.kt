package com.gongsung.auth

import com.gongsung.auth.persist.query.QueryAuthPersist
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val queryAuthPersist: QueryAuthPersist,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
//      Todo:  findByLoginId에서 loginId랑 password가 전부 빈칸으로 뜬다. 왜 ??
//          -> getById에 해당하는 User와 Company 또한 동일. 이거 고치고 테스트 고고
//        Spring Security 동작 방식도 낱낱이 파해쳐보자..!
        queryAuthPersist.findByLoginId(username)
            .let(::createUserDetails)

    private fun createUserDetails(account: Account): UserDetails =
        User(
            account.loginId,
            passwordEncoder.encode(account.password),
            mutableListOf<GrantedAuthority>().apply {
                add(
                    SimpleGrantedAuthority("AccountType_${account.type.name}"),
                )
            },
        )
}
