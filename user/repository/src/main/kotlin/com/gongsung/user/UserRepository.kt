package com.gongsung.user


import com.gongsung.user.entity.QUserEntity.userEntity
import com.gongsung.user.persist.CommandPersist
import com.gongsung.user.persist.QueryPersist
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class UserRepository(
    private val jpaQueryFactory: JPAQueryFactory
) : CommandPersist, QueryPersist, QuerydslRepositorySupport(User::class.java) {
    override fun create(user: UserProps): User {
        jpaQueryFactory.insert(userEntity)
            .columns(
                userEntity.loginId, userEntity.password, userEntity.name,
                userEntity.introduce, userEntity.email, userEntity.birthDate, userEntity.gender,
            )
            .values(user.loginId, user.password, user.name, user.introduce, user.email, user.birthDate, user.gender)
            .execute()

        return jpaQueryFactory.selectFrom(userEntity)
            .where(userEntity.loginId.eq(user.loginId))
            .fetchOne()!!
    }

    override fun delete(id: Long): Long {
        return jpaQueryFactory.delete(userEntity)
            .where(userEntity.id.eq(id)).execute()
    }

    override fun update(user: User): User {
        jpaQueryFactory.update(userEntity)
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
            .fetchOne()!!

    }

    override fun getById(id: Long): User {
        return jpaQueryFactory.selectFrom(userEntity)
            .where(userEntity.id.eq(id))
            .fetchOne()!!
    }

}