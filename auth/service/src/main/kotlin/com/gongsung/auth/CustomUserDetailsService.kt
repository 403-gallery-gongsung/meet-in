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
    override fun loadUserByUsername(username: String): UserDetails {
        val findByLoginId = queryAuthPersist.findByLoginId(username)
        requireNotNull(findByLoginId) {
            "there is no user"
        }

        return createUserDetails(findByLoginId)
    }

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
