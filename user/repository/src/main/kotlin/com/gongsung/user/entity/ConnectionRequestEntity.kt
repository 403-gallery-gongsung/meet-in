package com.gongsung.user.entity

import com.gongsung.user.ConnectionRequest
import com.gongsung.user.ConnectionRequestProps
import com.gongsung.user.enums.ConnectionRequestStatus
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "connection_request")
data class ConnectionRequestEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = JpaConstants.NOT_YET_SAVED,
    @Column(name = "from_user_id")
    override val fromUserId: Long,
    @Column(name = "to_user_id")
    override val toUserId: Long,
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    override val status: ConnectionRequestStatus = ConnectionRequestStatus.PENDING
) : ConnectionRequest {
    companion object {
        fun ofProps(connectionRequestProps: ConnectionRequestProps): ConnectionRequestEntity =
            ConnectionRequestEntity(
                fromUserId = connectionRequestProps.fromUserId,
                toUserId = connectionRequestProps.toUserId,
                status = connectionRequestProps.status,
            )
    }
}

object JpaConstants {
    const val NOT_YET_SAVED = -1L
}
