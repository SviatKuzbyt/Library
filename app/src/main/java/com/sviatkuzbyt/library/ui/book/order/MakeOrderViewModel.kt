package com.sviatkuzbyt.library.ui.book.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sviatkuzbyt.library.data.other.LabelData
import com.sviatkuzbyt.library.data.repositories.MakeOrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MakeOrderViewModel(id: Long, bookName: String, application: Application): AndroidViewModel(application) {
    private val repository = MakeOrderRepository(application, bookName)
    val dataList = MutableLiveData<List<LabelData>>()
    val error = MutableLiveData<String>()

    init { loadData() }

    private fun loadData() = viewModelScope.launch(Dispatchers.IO){
        try {
            val data = repository.getOrderData()
            withContext(Dispatchers.Main){
                dataList.postValue(data)
            }
        } catch (e: Exception){
            error.postValue(e.message)
        }
    }

    companion object {
        fun factory(id: Long, bookName: String, application: Application): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MakeOrderViewModel(id, bookName, application)
            }
        }
    }
}