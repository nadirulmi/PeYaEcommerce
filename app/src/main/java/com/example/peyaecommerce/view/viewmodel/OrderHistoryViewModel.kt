package com.example.peyaecommerce.view.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import com.example.peyaecommerce.model.database.dao.OrderDao
import com.example.peyaecommerce.model.database.relations.OrderWithItems
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val orderDao: OrderDao,
) : ViewModel() {

    val orders: Flow<List<OrderWithItems>> = orderDao.getAllOrders()
}
