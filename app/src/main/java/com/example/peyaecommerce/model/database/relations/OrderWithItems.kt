package com.example.peyaecommerce.model.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.peyaecommerce.model.database.entities.OrderEntity
import com.example.peyaecommerce.model.database.entities.OrderItemEntity

data class OrderWithItems(
    @Embedded val order: OrderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val items: List<OrderItemEntity>
)