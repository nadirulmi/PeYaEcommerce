package com.example.peyaecommerce.view.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyaecommerce.model.Product
import com.example.peyaecommerce.model.data.ProductDataBase
import com.example.peyaecommerce.model.data.ProductEntity
import com.example.peyaecommerce.model.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val dao = ProductDataBase.getDatabase(application).itemDao()

    val productsFlow: Flow<List<ProductEntity>> = dao.getAllItems()

    var filteredProducts by mutableStateOf<List<ProductEntity>>(emptyList())
        private set

    var searchQuery by mutableStateOf("")
    var selectedCategory by mutableStateOf("Todos")
    var priceOrder by mutableStateOf("Ninguno")

    init {
        viewModelScope.launch {
            productsFlow.collect { products ->
                filterProducts(products)
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        refilter()
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
        refilter()
    }

    fun onPriceOrderSelected(order: String) {
        priceOrder = order
        refilter()
    }

    private fun refilter() {
        viewModelScope.launch {
            productsFlow.firstOrNull()?.let { filterProducts(it) }
        }
    }

    private fun filterProducts(allProducts: List<ProductEntity>) {
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
