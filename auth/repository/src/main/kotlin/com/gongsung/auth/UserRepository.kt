package com.gongsung.auth

import com.gongsung.auth.entity.QUserEntity.userEntity
import com.gongsung.auth.entity.UserEntity
import com.gongsung.auth.persist.command.CommandUserPersist
import com.gongsung.auth.persist.query.QueryUserPersist
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class UserRepository(
    @PersistenceContext
    private val entityManager: EntityManager
) : QueryUserPersist, CommandUserPersist, QuerydslRepositorySupport(User::class.java) {

    private val jpaQueryFactory: JPAQueryFactory by lazy { JPAQueryFactory(entityManager) }
    override fun getUserById(id: Long): User =
        jpaQueryFactory.selectFrom(userEntity)
            .where(userEntity.id.eq(id))
            .fetchOne()!!

    override fun createUser(user: UserProps): User =
        user.let(UserEntity::ofProps)
            .also { entityManager.persist(it) }

}