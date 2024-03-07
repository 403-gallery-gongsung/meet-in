package com.gongsung.user

import com.gongsung.user.entity.QUserEntity.userEntity
import com.gongsung.user.entity.UserEntity
import com.gongsung.user.persist.CommandUserPersist
import com.gongsung.user.persist.QueryUserPersist
import com.querydsl.jpa.impl.JPAQueryFactory
import com.querydsl.jpa.impl.JPAUpdateClause
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserRepository(
    @PersistenceContext
    private val entityManager: EntityManager,
) : CommandUserPersist, QueryUserPersist, QuerydslRepositorySupport(User::class.java) {
    private val jpaQueryFactory by lazy { JPAQueryFactory(entityManager) }
    override fun createUser(userProps: UserProps): User {
        return userProps.let(UserEntity::ofProps)
            .let {
                entityManager.persist(it)
                it
            }
    }

    @Transactional
    override fun deleteUser(id: Long): Boolean {
        return jpaQueryFactory.delete(userEntity)
            .where(userEntity.id.eq(id)).execute() == 1L
    }

    @Transactional
    override fun updateUser(user: User): User {
        JPAUpdateClause(entityManager, userEntity)
            .where(userEntity.id.eq(user.userIdentity))
            .set(userEntity.loginId, user.loginId)
            .set(userEntity.password, user.password)
            .set(userEntity.email, user.email)
            .set(userEntity.birthDate, user.birthDate)
            .set(userEntity.gender, user.gender)
            .set(userEntity.introduce, user.introduce)
            .set(userEntity.name, user.name)
            .execute()

        return jpaQueryFactory.selectFrom(userEntity)
            .where(userEntity.id.eq(user.userIdentity))
            .fetchOne() ?: throw EntityNotFoundException("User with id: ${user.userIdentity} not found")
    }

    override fun getUserById(id: Long): User {
        return jpaQueryFactory.selectFrom(userEntity)
            .where(userEntity.id.eq(id))
            .fetchOne()!!
    }
}
