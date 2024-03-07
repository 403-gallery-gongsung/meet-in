package com.gongsung.user.entity.converter

import com.gongsung.user.entity.base.EntityStatus
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class EntityStatusConverter : AttributeConverter<EntityStatus, Int> {
    override fun convertToDatabaseColumn(attribute: EntityStatus): Int {
        return attribute.code
    }

    override fun convertToEntityAttribute(dbData: Int): EntityStatus {
        return EntityStatus.of(dbData)
    }
}
