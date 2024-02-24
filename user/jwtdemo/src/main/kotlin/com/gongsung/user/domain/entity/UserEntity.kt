package com.gongsung.user.domain.entity

import com.gongsung.user.domain.dto.UserDtoResponse
import com.gongsung.user.domain.enums.Gender
import com.gongsung.user.domain.enums.Role
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.hibernate.annotations.Fetch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
class UserEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    val loginId: String,

    @Column(nullable = false, length = 30)
    val email: String,

    @Column(nullable = false, length = 30)
    val password: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Temporal(TemporalType.DATE)
    val birthDate: LocalDate,

    @Enumerated(EnumType.STRING)
    val gender: Gender,

    val introduce: String,

    val deleteStatus: Boolean
){
    fun toDto(): UserDtoResponse = UserDtoResponse(id = id!!,
        loginId = loginId,
        birthDate = birthDate.formatDate(),
        gander = gender.description,
        email = email,
        name = name)

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    val userRole: List<UserRole>? = null

    private fun LocalDate.formatDate(): String =
        this.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
}

@Entity
class UserRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: Role,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: UserEntity
)