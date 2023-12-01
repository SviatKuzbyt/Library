package com.sviatkuzbyt.library.ui.main.catalog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.data.repositories.CatalogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogViewModel(application: Application): AndroidViewModel(application) {
    private val repository = CatalogRepository(application)
    val recommendationList = MutableLiveData<List<BookRecycler>>()
    val categoryList = MutableLiveData<List<String>>()

    init {
        loadData()
    }
    private fun loadData() = viewModelScope.launch(Dispatchers.IO){
        val list = repository.getRecommendation()
        withContext(Dispatchers.Main){
            recommendationList.postValue(list)
            categoryList.postValue(repository.getCategoryList())
        }
    }
}