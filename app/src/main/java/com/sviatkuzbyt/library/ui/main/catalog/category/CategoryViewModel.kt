package com.sviatkuzbyt.library.ui.main.catalog.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.data.repositories.loadCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(private val category: String, private val application: Application): AndroidViewModel(application) {
    val list = MutableLiveData<List<BookRecycler>>()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch(Dispatchers.IO){
        val _list = loadCategory(application, category)
        withContext(Dispatchers.Main){
            list.postValue(_list)
        }
    }

    companion object {
        fun factory(application: Application, category: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CategoryViewModel(category, application)
            }
        }
    }
}