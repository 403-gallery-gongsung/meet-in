package com.gongsung.auth

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
    private val jpaQueryFactory: JPAQueryFactory
) : QueryUserPersist, CommandUserPersist {
    override fun getUserById(id: Long): User =
        jpaQueryFactory.selectFrom(userEntity)
            .where(userEntity.id.eq(id))
            .fetchOne()!!

    override fun createUser(user: UserProps): User =
        user.let(UserEntity::ofProps)
            .also { entityManager.persist(it) }

}