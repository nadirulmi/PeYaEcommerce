package com.example.peyaecommerce.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.peyaecommerce.model.data.OrderEntity
import com.example.peyaecommerce.model.data.OrderItemEntity

data class OrderWithItems(
    @Embedded val order: OrderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val items: List<OrderItemEntity>
)