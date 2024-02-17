package com.gongsung.user.entity.base

enum class EntityStatus(val code: Int) {
    NORMAL(100),
    DELETED(0);

    companion object {
        fun of(code: Int) = entries.find { it.code == code } ?: throw RuntimeException("Status code is not matched.")
    }
}
