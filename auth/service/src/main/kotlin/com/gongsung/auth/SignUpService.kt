package com.gongsung.auth

import com.gongsung.auth.command.SignUpUseCase
import com.gongsung.auth.persist.command.CommandAuthPersist
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpService(
    private val commandAuthPersist: CommandAuthPersist
) : SignUpUseCase {

    @Transactional
    override fun signUpUser(accountProps: AccountProps, userProps: UserProps): UserAccount {
//        Todo : feign을 통해 userProps를 User module에게 전달 후 User module에서 userProps 저장.

        return accountProps.let(commandAuthPersist::createAccount)
            .run {
                UserAccount.of(
                    AccountIdentity.of(accountIdentity),
                    UserAccountProps.of(
                        accountProps = AccountProps.of(
                            loginId,
                            password,
                            type,
                        ),
                        userProps = UserProps.of(
                            email = userProps.email,
                            name = userProps.name,
                            birthDate = userProps.birthDate,
                            gender = userProps.gender,
                            introduce = userProps.introduce,
                        ),
                    ),
                )
            }
    }

    @Transactional
    override fun signUpCompany(accountProps: AccountProps, companyProps: CompanyProps): CompanyAccount {
//        Todo : feign을 통해 companyProps를 company module에게 전달 후 company module에서 companyProps 저장.

        return accountProps.let(commandAuthPersist::createAccount)
            .run {
                CompanyAccount.of(
                    AccountIdentity.of(accountIdentity),
                    CompanyAccountProps.of(
                        accountProps = AccountProps.of(
                            loginId,
                            password,
                            type
                        ),

                        companyProps = CompanyProps.of(
                            name = companyProps.name,
                            address = companyProps.address,
                            website = companyProps.website,
                            introduce = companyProps.introduce,
                        )
                    )
                )
            }
    }


}