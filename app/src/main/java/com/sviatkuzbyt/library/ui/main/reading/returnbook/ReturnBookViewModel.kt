package com.sviatkuzbyt.library.ui.main.reading.returnbook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sviatkuzbyt.library.data.other.LabelData
import com.sviatkuzbyt.library.data.repositories.ReturnBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReturnBookViewModel(private val id: Long, private val bookName: String, application: Application): AndroidViewModel(application) {
    private val repository = ReturnBookRepository(application)
    val dataList = MutableLiveData<List<LabelData>>()
    val successful = MutableLiveData<Boolean>()

    init { loadData() }

    private fun loadData() = viewModelScope.launch(Dispatchers.IO){
        try {
            val _list = repository.getReturnData(bookName)
            withContext(Dispatchers.Main){
                dataList.postValue(_list)
            }
        } catch (_: Exception){
            successful.postValue(false)
        }
    }

    fun makeReturn()  = viewModelScope.launch(Dispatchers.IO){
        try {
            repository.makeReturn(id)
            successful.postValue(true)
        } catch (_: Exception){
            successful.postValue(false)
        }
    }

    companion object {
        fun factory(id: Long, bookName: String, application: Application): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ReturnBookViewModel(id, bookName, application)
            }
        }
    }
}