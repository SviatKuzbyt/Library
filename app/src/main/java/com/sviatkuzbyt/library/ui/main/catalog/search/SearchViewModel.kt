package com.sviatkuzbyt.library.ui.main.catalog.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.data.repositories.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(application: Application): AndroidViewModel(application) {
    private val repository = SearchRepository(application)
    val list = MutableLiveData<List<BookRecycler>>()

    fun search(name: String) = viewModelScope.launch(Dispatchers.IO){
        val _list = repository.loadBooks(name)
        withContext(Dispatchers.Main){
            list.postValue(_list)
        }
    }
}