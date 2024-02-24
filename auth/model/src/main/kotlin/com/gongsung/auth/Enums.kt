package com.gongsung.auth

enum class AccountType {
    COMPANY, USER
}

enum class Gender{
    UNKNOWN, MALE, FEMALE
}

enum class Position(val link: String){
    Tech("/tech/"),
    Design("/design/")
}