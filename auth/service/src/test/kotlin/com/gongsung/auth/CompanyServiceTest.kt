package com.gongsung.auth

import com.gongsung.auth.lookup.CompanyLookUpService
import com.gongsung.auth.persist.command.CommandCompanyPersist
import com.gongsung.auth.persist.query.QueryCompanyPersist
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CompanyServiceTest : BehaviorSpec(
    {
        given("Company / CompanyLookUpService sut이 주어진다") {
            val commandCompanyPersist = mock<CommandCompanyPersist>()
            val sut = CompanyService(commandCompanyPersist)

            val queryCompanyPersist = mock<QueryCompanyPersist>()
            val lookUpSut = CompanyLookUpService(queryCompanyPersist)

            `when`("companyService의 createCompany를 호출할 때") {
                val companyProps = CompanyProps.of(
                    loginId = "meet-in",
                    password = "KNU2016!@#",
                    name = "gongsung",
                    website = "gallery.com",
                    introduce = "hello world!",
                )
                val expected = Company.of(CompanyIdentity.of(1L), companyProps)

                whenever(sut.createCompany(companyProps)).thenReturn(expected)

                then("expected와 검증") {
                    val result = sut.createCompany(companyProps)
                    result shouldBe expected
                }
            }

            `when`("CompanyLookUpService의 getCompanyById를 호출할 때") {
                val companyId = 1L
                val expected = Company.of(
                    CompanyIdentity.of(companyId),
                    CompanyProps.of(
                        loginId = "meet-in",
                        password = "KNU2016!@#",
                        name = "gongsung",
                        website = "gallery.com",
                        introduce = "hello world!",
                    ),
                )

                whenever(lookUpSut.getCompanyById(CompanyIdentity.of(companyId))).thenReturn(expected)

                then("expected와 검증") {
                    val result = lookUpSut.getCompanyById(CompanyIdentity.of(companyId))
                    result shouldBe expected
                }
            }
        }
    },
)