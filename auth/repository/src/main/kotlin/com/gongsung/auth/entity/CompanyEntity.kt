package com.gongsung.auth.entity

import com.gongsung.auth.AccountType
import com.gongsung.auth.Company
import com.gongsung.auth.CompanyProps
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Pattern
import org.springframework.validation.annotation.Validated

@Entity
@Validated
@Table(name = "COMPANY")
class CompanyEntity(
    @Column(nullable = false, length = 30)
    override val loginId: String = "",

    @Column(nullable = false, length = 30)
    @field:Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,15}$",
        message = "영어 대소문자 및 숫자, 특수기호가 1개 이상 포함된 8~15길이의 비밀번호여야 합니다.",
    )
    override var password: String = "",

    @Column(nullable = false, length = 10)
    override var name: String = "",
    override var website: String = "",
    override var introduce: String = "",

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var jobPostings: MutableList<JobEntity> = mutableListOf()
) : Company, AccountEntity(loginId = loginId, password = password, type = AccountType.COMPANY) {
    companion object {
        fun ofProps(companyProps: CompanyProps) = CompanyEntity(
            loginId = companyProps.loginId,
            password = companyProps.password,
            name = companyProps.name,
            website = companyProps.website,
            introduce = companyProps.introduce,
        )
    }

    override val companyIdentity: Long
        get() = id
}