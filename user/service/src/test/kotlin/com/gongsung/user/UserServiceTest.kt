package com.gongsung.user

import com.gongsung.user.enums.Gender
import com.gongsung.user.persist.CommandUserPersist
import com.gongsung.user.persist.QueryUserPersist
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import java.time.LocalDate

class UserServiceTest {
    @Test
    fun `유저 등록`() {
//        given
        val userProps =
            UserProps.of(
                loginId = "yeollow",
                password = "Password123!",
                email = "gongsung123@gmail.com",
                name = "knu",
                birthDate = LocalDate.of(1998, 1, 5),
                gender = Gender.UNKNOWN,
                introduce = "my name is",
            )

        val expected =
            User.of(
                identity = UserIdentity.of(1L),
                props = userProps,
            )

//        when
        val commandUserPersist =
            mock<CommandUserPersist> {
                on { createUser(userProps) } doReturn expected
            }
        val userService = UserService(commandUserPersist)
        val createdUser = userService.createUser(userProps)

        val queryUserPersist =
            mock<QueryUserPersist> {
                on { getUserById(1L) } doReturn expected
            }
        val userLookUpService = UserLookUpService(queryUserPersist)
        val result = userLookUpService.getUserById(UserIdentity.of(1L))

//        then
        assertEquals(createdUser.userIdentity, 1L)
        assertEquals(result.userIdentity, 1L)
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("email 및 password 형식 확인")
    fun `email 및  password 형식 확인`() {
//        given
        val incorrectEmailUserProps =
            UserProps.of(
                loginId = "yeollow",
                password = "Password123!",
                email = "gongsung123",
                name = "knu",
                birthDate = LocalDate.of(1998, 1, 5),
                gender = Gender.UNKNOWN,
                introduce = "my name is",
            )

        val incorrectPasswordUserProps =
            UserProps.of(
                loginId = "yeollow",
                password = "password123!",
                email = "gongsung123@gmail.com",
                name = "knu",
                birthDate = LocalDate.of(1998, 1, 5),
                gender = Gender.UNKNOWN,
                introduce = "my name is",
            )

//        when
        val emailUserPersist =
            mock<CommandUserPersist> {
                on { createUser(incorrectEmailUserProps) } doThrow RuntimeException("이메일 형식이 잘못됐습니다")
            }

        val emailUserService = UserService(emailUserPersist)

        val passwordUserPersist =
            mock<CommandUserPersist> {
                on { createUser(incorrectPasswordUserProps) } doThrow RuntimeException("비밀번호 형식이 잘못됐습니다")
            }

        val passwordUserService = UserService(passwordUserPersist)

//        then
        assertThrows(Exception::class.java) {
            emailUserService.createUser(incorrectEmailUserProps)
        }

        assertThrows(Exception::class.java) {
            passwordUserService.createUser(incorrectPasswordUserProps)
        }
    }

    @Test
    fun `id로 User 조회`() {
//        given
        val id = 1L
        val expected =
            User.of(
                identity = UserIdentity.of(id),
                props =
                    UserProps.of(
                        loginId = "yeollow",
                        password = "Yeollow123!@#",
                        email = "gongsung123@gmail.com",
                        name = "knu",
                        birthDate = LocalDate.of(1998, 1, 5),
                        gender = Gender.MALE,
                        introduce = "yeollow world !",
                    ),
            )

        val queryUserPersist =
            mock<QueryUserPersist> {
                on { getUserById(id) } doReturn expected
            }
        val userLookUpService = UserLookUpService(queryUserPersist)

//        when
        val result = userLookUpService.getUserById(UserIdentity.of(id))

//        then
        assertEquals(id, result.userIdentity)
        assertEquals(expected, result)
    }

    @Test
    fun `유저 정보 변경`() {
//        given
        val id = 1L
        val beforeUpdate =
            User.of(
                identity = UserIdentity.of(id),
                props =
                    UserProps.of(
                        loginId = "ljy2784437",
                        password = "jyLee2784437!@#",
                        email = "gongsung123@gmail.com",
                        name = "knu",
                        birthDate = LocalDate.of(1998, 1, 5),
                        gender = Gender.MALE,
                        introduce = "yeollow world !",
                    ),
            )

        val afterUpdate =
            User.of(
                identity = UserIdentity.of(id),
                props =
                    UserProps.of(
                        loginId = "yeollow",
                        password = "Yeollow1588!@#",
                        email = "gongsung123@gmail.com",
                        name = "knu",
                        birthDate = LocalDate.of(1998, 1, 5),
                        gender = Gender.FEMALE,
                        introduce = "yeollow world !",
                    ),
            )

//        when
        val commandUserPersist =
            mock<CommandUserPersist> {
                on { updateUser(beforeUpdate) } doReturn afterUpdate
            }
        val userService = UserService(commandUserPersist)

        val result = userService.updateUser(beforeUpdate)

//        then
        assertEquals(afterUpdate, result)
        assertNotEquals(beforeUpdate, result)
    }

    @Test
    fun `유저 삭제`() {
//        given
        val id = 1L

//        when
        val deleteUserPersist =
            mock<CommandUserPersist> {
                on { deleteUser(id) } doReturn true
            }
        val userService = UserService(deleteUserPersist)

        val result = userService.deleteUser(UserIdentity.of(id))

//        then
        assertTrue(result)
    }
}
