package com.gongsung.user

import com.gongsung.user.entity.ConnectionRequestEntity
import com.gongsung.user.enums.ConnectionRequestStatus
import com.gongsung.user.persist.connection.ConnectionRequestCommandPersist
import com.gongsung.user.persist.connection.ConnectionRequestQueryPersist
import org.springframework.stereotype.Repository

@Repository
class ConnectionRequestRepository(
    private val jpaConnectionRequestRepository: JpaConnectionRequestRepository
) : ConnectionRequestCommandPersist, ConnectionRequestQueryPersist {
    override fun create(connectionRequestProps: ConnectionRequestProps): ConnectionRequest {
        return jpaConnectionRequestRepository.save(
            ConnectionRequestEntity.ofProps(
                connectionRequestProps,
            ),
        )
    }

    override fun update(connectionRequestProps: ConnectionRequestProps): ConnectionRequest {
        getByFromUserIdAndToUserId(
            UserIdentity.of(connectionRequestProps.fromUserId),
            UserIdentity.of(connectionRequestProps.toUserId),
        )?.let {
            return jpaConnectionRequestRepository.save(
                it.copy(
                    status = connectionRequestProps.status,
                ),
            )
        }
            ?: throw IllegalArgumentException("${connectionRequestProps.fromUserId} - ${connectionRequestProps.toUserId} Connection not found")

    }

    override fun getAllByUserId(userIdentity: UserIdentity, status: ConnectionRequestStatus): List<ConnectionRequest> {
        return jpaConnectionRequestRepository.findAllByFromUserIdAndStatus(
            userIdentity.userIdentity,
            status,
        )
    }

    override fun getByFromUserIdAndToUserId(
        fromUserIdentity: UserIdentity,
        toUserIdentity: UserIdentity
    ): ConnectionRequestEntity? {
        return jpaConnectionRequestRepository.findByFromUserIdAndToUserId(
            fromUserIdentity.userIdentity,
            toUserIdentity.userIdentity,
        ) ?: jpaConnectionRequestRepository.findByFromUserIdAndToUserId(
            toUserIdentity.userIdentity,
            fromUserIdentity.userIdentity,
        )
    }
}
