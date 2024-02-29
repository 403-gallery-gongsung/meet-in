package com.gongsung.auth

import com.gongsung.auth.entity.QAccountEntity.accountEntity
import com.gongsung.auth.entity.QUserEntity.userEntity
import com.gongsung.auth.entity.UserEntity
import com.gongsung.auth.persist.command.CommandUserPersist
import com.gongsung.auth.persist.query.QueryUserPersist
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class UserRepository(
    @PersistenceContext
    private val entityManager: EntityManager,
    private val jpaQueryFactory: JPAQueryFactory,
) : QueryUserPersist, CommandUserPersist {
    override fun getUserById(id: Long): User =
        jpaQueryFactory
            .select(
                userEntity.loginId,
                userEntity.password,
                userEntity.email,
                userEntity.name,
                userEntity.birthDate,
                userEntity.gender,
                userEntity.introduce,
            )
            .from(userEntity)
            .join(accountEntity).on(userEntity.id.eq(accountEntity.id))
            .fetchOne()
            ?.let {
                User.of(
                    UserIdentity.of(id),
                    UserProps.of(
                        loginId = it.get(userEntity.loginId)!!,
                        password = it.get(userEntity.password)!!,
                        email = it.get(userEntity.email)!!,
                        name = it.get(userEntity.name)!!,
                        birthDate = it.get(userEntity.birthDate),
                        gender = it.get(userEntity.gender),
                        introduce = it.get(userEntity.introduce),
                    ),
                )
            } ?: throw NoSuchElementException("User not Found with Id: $id")

    override fun createUser(user: UserProps): User =
        user.let(UserEntity::ofProps)
            .also { entityManager.persist(it) }
}
