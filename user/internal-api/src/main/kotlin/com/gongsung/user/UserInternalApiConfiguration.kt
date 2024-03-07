import com.gongsung.user.UserInternalApi
import com.gongsung.user.command.CommandUserUseCase
import com.gongsung.user.query.QueryUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserInternalApiConfiguration {
    @Bean
    fun userInternalApi(
        commandUserUseCase: CommandUserUseCase,
        queryUserUseCase: QueryUserUseCase,
    ): UserInternalApi {
        return UserInternalApi(commandUserUseCase, queryUserUseCase)
    }
}
