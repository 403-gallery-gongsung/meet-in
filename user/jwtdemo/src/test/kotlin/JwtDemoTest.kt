import com.gongsung.user.domain.dto.LoginDto
import com.gongsung.user.repository.UserRepository
import com.gongsung.user.service.UserService
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class JwtDemoTest(
    private val userService: UserService,
    private val userRepository: UserRepository,
) {
    @Test
    fun passwordEncoding() {
        // given
        val loginDto = LoginDto("gongsung403", "cafeConcrete123")

        // when
        val signInTokenInfo = userService.signIn(loginDto)

        // then
        val findUser = userRepository.findByLoginId(loginDto.loginId)
        if (findUser != null) {
            assertNotEquals(loginDto.password, findUser.password)

            println(
                "Input password : ${loginDto.password} : " +
                    "Output password : ${findUser.password}\n",
            )

            println("TokenInfo : ${signInTokenInfo.accessToken}, ${signInTokenInfo.grantType}")
        }
    }
}
