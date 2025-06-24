package com.example.peyaecommerce.view.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.peyaecommerce.view.viewmodel.ProductListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun CartScreen(
    navController: NavHostController,
    viewModel: ProductListViewModel
) {
    val cartItems = viewModel.cartItems
    Log.d("Carrito", "Items en el carrito: ${cartItems}")
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cartItems) { item ->
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.product.imagenResId),
                        contentDescription = item.product.nombre,
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item.product.nombre, fontWeight = FontWeight.Bold)
                        Text(text = "Precio: $${item.product.precio}")
                    }

                    // Cantidad + / -
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { viewModel.decreaseQuantity(item.product) }) {
                            Icon(Icons.Default.Remove, contentDescription = "Reducir")
                        }
                        Text("${item.quantity}")
                        IconButton(onClick = { viewModel.increaseQuantity(item.product) }) {
                            Icon(Icons.Default.Add, contentDescription = "Aumentar")
                        }
                    }

                    // Eliminar
                    IconButton(onClick = { viewModel.removeFromCart(item.product) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
            }
        }
    }
}
