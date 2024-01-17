package com.gongsung.user.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gongsung.common.annotation.ValidEnum
import com.gongsung.user.domain.entity.UserEntity
import com.gongsung.user.domain.enums.Gender
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class UserDto(

    val id: Long?,

    @field:NotBlank
    val loginId: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = "영문, 숫자, 특수문자를 포함한 8~20자리로 입력해주세요",
    )
    val password: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:ValidEnum(enumClass = Gender::class, message = "MAN이나 WOMAN 중 하나를 선택해주세요")
    @JsonProperty("gender")
    private val _gender: String?,

    @JsonProperty("birthDate")
    private val _birthDate: String?,
    val introduce: String
) {
    val gender: Gender
        get() = Gender.valueOf(_gender!!)

    val birthDate: LocalDate
        get() = _birthDate!!.toLocalDate()


    private fun String.toLocalDate(): LocalDate =
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    fun toEntity(): UserEntity =
        UserEntity(
            id = id,
            loginId = loginId,
            password = password,
            email = email,
            name = name,
            gender = gender,
            birthDate = birthDate,
            introduce = introduce,
            deleteStatus = false
        )

}