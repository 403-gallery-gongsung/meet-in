package com.gongsung.connection

import com.gongsung.connection.command.ConnectionRequestCommandUseCase
import com.gongsung.connection.query.ConnectionRequestQueryUseCase
import com.gongsung.follow.FollowService
import com.gongsung.user.ConnectionRequest
import com.gongsung.user.ConnectionRequestProps
import com.gongsung.user.FollowToUserProps
import com.gongsung.user.UserIdentity
import com.gongsung.user.enums.ConnectionRequestStatus
import com.gongsung.user.persist.connection.ConnectionRequestCommandPersist
import com.gongsung.user.persist.connection.ConnectionRequestQueryPersist
import org.springframework.stereotype.Service

@Service
class ConnectionRequestService(
    private val connectionRequestCommandPersist: ConnectionRequestCommandPersist,
    private val connectionRequestQueryPersist: ConnectionRequestQueryPersist,
    private val followService: FollowService
) : ConnectionRequestCommandUseCase, ConnectionRequestQueryUseCase {

    override fun getAllConnectionByUserId(userIdentity: UserIdentity): List<ConnectionRequest> {
        return connectionRequestQueryPersist.getAllByUserId(userIdentity, ConnectionRequestStatus.ACCEPTED)
    }

    override fun getByFromUserIdAndToUserId(
        fromUserIdentity: UserIdentity,
        toUserIdentity: UserIdentity
    ): ConnectionRequest? {
        return connectionRequestQueryPersist.getByFromUserIdAndToUserId(fromUserIdentity, toUserIdentity)
    }

    override fun create(fromUserIdentity: UserIdentity, toUserIdentity: UserIdentity): ConnectionRequest {
        if (getByFromUserIdAndToUserId(fromUserIdentity, toUserIdentity) != null) {
            throw IllegalArgumentException("ConnectionRequest already exists")
        }
        return connectionRequestCommandPersist.create(
            ConnectionRequestProps.of(
                fromUserId = fromUserIdentity.userIdentity,
                toUserId = toUserIdentity.userIdentity,
                status = ConnectionRequestStatus.PENDING,
            ),
        )
    }

    override fun update(connectionRequestProps: ConnectionRequestProps): ConnectionRequest {
        if (connectionRequestProps.status == ConnectionRequestStatus.ACCEPTED) {
            followService.followToUser(
                FollowToUserProps.of(
                    fromUserId = connectionRequestProps.fromUserId,
                    toUserId = connectionRequestProps.toUserId,
                )
            )
        }
        return connectionRequestCommandPersist.update(connectionRequestProps)
    }
}
