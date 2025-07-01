package com.example.peyaecommerce.view.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.room.FtsOptions
import com.example.peyaecommerce.model.data.OrderDao
import com.example.peyaecommerce.model.relations.OrderWithItems
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val orderDao: OrderDao,
) : ViewModel() {

    val orders: Flow<List<OrderWithItems>> = orderDao.getAllOrders()
}
