package com.example.peyaecommerce.view.ui.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import com.example.peyaecommerce.view.viewmodel.ProductListViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.peyaecommerce.model.database.mappers.toProduct
import com.example.peyaecommerce.view.ui.components.ProductCard
import com.example.peyaecommerce.view.viewmodel.CartViewModel

@Composable
fun ProductCatalogScreen(
    navController: NavController,
    productListViewModel: ProductListViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val products = productListViewModel.filteredProducts
    val searchQuery = productListViewModel.searchQuery
    val selectedCategory = productListViewModel.selectedCategory
    val priceOrder = productListViewModel.priceOrder

    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF4A0D22),
                    shape = RoundedCornerShape(
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { productListViewModel.onSearchQueryChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Filtros
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            // Categoría
//            DropdownMenuFiltro(
//                label = "Categoría",
//                options = listOf("Todos", "Comida"),
//                selected = selectedCategory,
//                onSelected = { viewModel.onCategorySelected(it) }
//            )
//
//            // Precio
//            DropdownMenuFiltro(
//                label = "Precio",
//                options = listOf("Ninguno", "Ascendente", "Descendente"),
//                selected = priceOrder,
//                onSelected = { viewModel.onPriceOrderSelected(it) }
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(14.dp)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products) { productEntity ->
                    val product = productEntity.toProduct()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                    ) {
                        ProductCard(
                            product = product,
                            onAddClick = {
                                println("Agregado: ${product.nombre}")
                                cartViewModel.addToCart(product)
                                Log.d("Carrito", "Items agregado: ${product}")
                            }
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun DropdownMenuFiltro(
    label: String,
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label)
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(selected)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(text = { Text(option) }, onClick = {
                        onSelected(option)
                        expanded = false
                    })
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Buscar producto") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar"
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50),
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 50.dp)
            .padding(2.dp)
    )
}







