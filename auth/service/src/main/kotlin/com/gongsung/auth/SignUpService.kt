package com.gongsung.auth

import com.gongsung.auth.command.SignUpUseCase
import com.gongsung.auth.persist.command.CommandAuthPersist
import com.gongsung.user.UserFeignClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpService(
    private val commandAuthPersist: CommandAuthPersist,
    private val userFeignClient: UserFeignClient,
) : SignUpUseCase {

    @Transactional
    override fun signUpUser(accountProps: AccountProps, userProps: UserProps): UserAccount {
        return accountProps.let(commandAuthPersist::createAccount)
            .run {
                val userInternalRequest = UserProps.of(
                    accountId = accountIdentity,
                    email = userProps.email,
                    name = userProps.name,
                    birthDate = userProps.birthDate,
                    gender = userProps.gender,
                    introduce = userProps.introduce,
                )


                userFeignClient.createUser(userInternalRequest)

                UserAccount.of(
                    AccountIdentity.of(accountIdentity),
                    UserAccountProps.of(
                        accountProps = AccountProps.of(
                            loginId,
                            password,
                            type,
                        ),
                        userProps = userInternalRequest,
                    ),
                    )
            }
    }

    @Transactional
    override fun signUpCompany(accountProps: AccountProps, companyProps: CompanyProps): CompanyAccount {
//        Todo : write Feign like User

        return accountProps.let(commandAuthPersist::createAccount)
            .run {
                CompanyAccount.of(
                    AccountIdentity.of(accountIdentity),
                    CompanyAccountProps.of(
                        accountProps = AccountProps.of(
                            loginId,
                            password,
                            type,
                        ),

                        companyProps = CompanyProps.of(
                            name = companyProps.name,
                            address = companyProps.address,
                            website = companyProps.website,
                            introduce = companyProps.introduce,
                        ),
                    ),
                )
            }
    }


}