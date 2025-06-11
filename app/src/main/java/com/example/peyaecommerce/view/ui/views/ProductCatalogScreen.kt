package com.example.peyaecommerce.view.ui.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import com.example.peyaecommerce.view.viewmodel.ProductListViewModel
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProductCatalogScreen(navController: NavHostController, viewModel: ProductListViewModel = viewModel()) {
    val products = viewModel.filteredProducts
    val searchQuery = viewModel.searchQuery
    val selectedCategory = viewModel.selectedCategory
    val priceOrder = viewModel.priceOrder

    Column(modifier = Modifier.padding(16.dp)) {

        // Buscador
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            label = { Text("Buscar producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Filtros
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Categoría
            DropdownMenuFiltro(
                label = "Categoría",
                options = listOf("Todos", "Comida"),
                selected = selectedCategory,
                onSelected = { viewModel.onCategorySelected(it) }
            )

            // Precio
            DropdownMenuFiltro(
                label = "Precio",
                options = listOf("Ninguno", "Ascendente", "Descendente"),
                selected = priceOrder,
                onSelected = { viewModel.onPriceOrderSelected(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(products) { product ->
                ProductCard(product = product)
                Spacer(modifier = Modifier.height(8.dp))
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



