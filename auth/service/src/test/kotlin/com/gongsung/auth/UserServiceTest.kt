package com.gongsung.auth

import com.gongsung.auth.lookup.CompanyLookUpService
import com.gongsung.auth.lookup.UserLookUpService
import com.gongsung.auth.persist.command.CommandCompanyPersist
import com.gongsung.auth.persist.command.CommandUserPersist
import com.gongsung.auth.persist.query.QueryCompanyPersist
import com.gongsung.auth.persist.query.QueryUserPersist
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.Test
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate


@SpringBootTest
class UserServiceTest : BehaviorSpec(
    {
        given("User / UserLookUpService sut이 주어진다") {
            val commandUserPersist = mock<CommandUserPersist>()
            val sut = UserService(commandUserPersist)

            val queryUserPersist = mock<QueryUserPersist>()
            val lookUpSut = UserLookUpService(queryUserPersist)

            `when`("userService의 createUser를 호출할 때") {
                val userProps = UserProps.of(
                    loginId = "yeollow",
                    password = "Password123!",
                    email = "gongsung123@gmail.com",
                    name = "knu",
                    birthDate = LocalDate.of(1998, 1, 5),
                    gender = Gender.UNKNOWN,
                    introduce = "my name is",
                )
                val expected = User.of(UserIdentity.of(1L), userProps)

                whenever(sut.createUser(userProps)).thenReturn(expected)

                then("expected와 검증") {
                    val result = sut.createUser(userProps)
                    result shouldBe expected
                }
            }

            `when`("UserLookUpService의 getUserById를 호출할 때"){
                val userId = 1L
                val expected = User.of(
                    UserIdentity.of(userId),
                    UserProps.of(
                        loginId = "yeollow",
                        password = "Password123!",
                        email = "gongsung123@gmail.com",
                        name = "knu",
                        birthDate = LocalDate.of(1998, 1, 5),
                        gender = Gender.UNKNOWN,
                        introduce = "my name is",
                    ),
                )

                whenever(lookUpSut.getUserById(UserIdentity.of(userId))).thenReturn(expected)

                then("expected와 검증"){
                    val result = lookUpSut.getUserById(UserIdentity.of(userId))
                    result shouldBe expected
                }
            }
        }
    },
)