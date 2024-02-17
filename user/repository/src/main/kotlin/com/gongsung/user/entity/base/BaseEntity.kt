package com.gongsung.user.entity.base

import com.gongsung.user.entity.converter.EntityStatusConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity : TimeAuditingEntity() {
    @Convert(converter = EntityStatusConverter::class)
    @Column(name = "status")
    var status: EntityStatus = EntityStatus.NORMAL
}
