package com.example.peyaecommerce.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.peyaecommerce.data.repository.ProductRepository

class ProductListViewModel : ViewModel() {

    var searchQuery by mutableStateOf("")
    var selectedCategory by mutableStateOf("Todos")
    var priceOrder by mutableStateOf("Ninguno") // "Ascendente", "Descendente", "Ninguno"

    private val allProducts = ProductRepository.products
    var filteredProducts by mutableStateOf(allProducts)
        private set

    init {
        filterProducts()
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        filterProducts()
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
        filterProducts()
    }

    fun onPriceOrderSelected(order: String) {
        priceOrder = order
        filterProducts()
    }

    private fun filterProducts() {
        var result = allProducts.filter {
            it.nombre.contains(searchQuery, ignoreCase = true)
        }

        if (selectedCategory != "Todos") {
            result = result.filter { it.categoria == selectedCategory }
        }

        result = when (priceOrder) {
            "Ascendente" -> result.sortedBy { it.precio }
            "Descendente" -> result.sortedByDescending { it.precio }
            else -> result
        }

        filteredProducts = result
    }
}
