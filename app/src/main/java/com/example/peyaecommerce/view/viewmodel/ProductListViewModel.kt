package com.example.peyaecommerce.view.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyaecommerce.model.data.remote.ApiService
import com.example.peyaecommerce.model.database.mappers.toProduct
import com.example.peyaecommerce.model.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    var products by mutableStateOf<List<Product>>(emptyList())
        private set

    var filteredProducts by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var searchQuery by mutableStateOf("")
    var selectedCategory by mutableStateOf("Todos")
    var priceOrder by mutableStateOf("Ninguno")

    val categories = listOf("Todos", "Pollo", "Carne", "Pescado", "Pizza", "Ensaladas", "Otros")

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = apiService.getFoods()
                if (response.isSuccessful) {
                    products = response.body()?.map { it.toProduct() }.orEmpty()
                    filterProducts()
                }
            } catch (e: Exception) {
                Log.e("API_TEST", "Error al hacer la petición", e)
            } finally {
                isLoading = false
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        filterProducts()
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
        filterProducts()
    }

    private fun filterProducts() {
        var result = products.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }

        if (selectedCategory != "Todos") {
            result = result.filter { it.category == selectedCategory }
        }

        result = when (priceOrder) {
            "Ascendente" -> result.sortedBy { it.price }
            "Descendente" -> result.sortedByDescending { it.price }
            else -> result
        }

        filteredProducts = result
    }
}
