package com.example.peyaecommerce.view.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peyaecommerce.model.database.relations.OrderWithItems
import com.example.peyaecommerce.view.viewmodel.OrderHistoryViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun OrderHistoryScreen(
    navController: NavHostController,
    orderHistoryViewModel: OrderHistoryViewModel = hiltViewModel()
) {
    val orders by orderHistoryViewModel.orders.collectAsState(initial = emptyList())

    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Historial de Órdenes",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(Modifier.height(16.dp))

            if (orders.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay órdenes registradas aún.", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(items = orders, key = { it.order.id }) { orderWithItems ->
                        OrderCardAnimated(orderWithItems)
                    }
                }
            }
        }
    }
}

@Composable
fun OrderCardAnimated(orderWithItems: OrderWithItems) {
    val order = orderWithItems.order
    val dateFormatted = remember(order.date) {
        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(order.date))
    }

    val customColor = Color(0xFF7B2641)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = getFoodIcon(orderWithItems.items.firstOrNull()?.nombre ?: ""),
                contentDescription = null,
                tint = customColor,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = customColor.copy(alpha = 0.1f),
                        shape = CircleShape
                    )
                    .padding(8.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Total: $${"%.2f".format(order.total)}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = customColor
                    )
                )
                Text(
                    text = "Fecha: $dateFormatted",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Spacer(Modifier.height(8.dp))
                orderWithItems.items.forEach { item ->
                    Text(
                        "- ${item.nombre} x${item.cantidad}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

fun getFoodIcon(name: String): ImageVector {
    return when {
        name.contains("hamburguesa", true) -> Icons.Default.LunchDining
        name.contains("ensalada", true) -> Icons.Default.Eco
        name.contains("taco", true) -> Icons.Default.LocalDining
        name.contains("burrito", true) -> Icons.Default.RestaurantMenu
        else -> Icons.Default.Fastfood
    }
}

