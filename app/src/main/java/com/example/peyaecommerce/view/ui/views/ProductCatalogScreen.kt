package com.example.peyaecommerce.view.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import com.example.peyaecommerce.view.viewmodel.ProductListViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.peyaecommerce.view.ui.components.ProductCard
import com.example.peyaecommerce.view.viewmodel.CartViewModel
import com.example.peyaecommerce.view.ui.components.MenuSkeletonLoader
import kotlinx.coroutines.launch

@Composable
fun ProductCatalogScreen(
    navController: NavController,
    productListViewModel: ProductListViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val products = productListViewModel.filteredProducts
    val searchQuery = productListViewModel.searchQuery
    val isLoading = productListViewModel.isLoading

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
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

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),

                    contentAlignment = Alignment.Center
                ) {
                    MenuSkeletonLoader()
                }
            } else {
                LazyRow(
                    modifier = Modifier
                        //bg blanco
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(productListViewModel.categories) { category ->
                        FilterChip(
                            selected = category == productListViewModel.selectedCategory,
                            onClick = { productListViewModel.onCategorySelected(category) },
                            label = { Text(category) }
                        )
                    }
                }

                Column(modifier = Modifier.padding(14.dp)) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(products) { product ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(260.dp)
                            ) {
                                ProductCard(
                                    product = product,
                                    onAddClick = {
                                        cartViewModel.addToCart(product)
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("${product.name} añadido al carrito")
                                        }
                                    }
                                )
                            }
                        }
                    }
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







