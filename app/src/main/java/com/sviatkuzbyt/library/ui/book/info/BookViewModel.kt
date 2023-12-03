package com.sviatkuzbyt.library.ui.book.info

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.data.other.LabelData
import com.sviatkuzbyt.library.data.repositories.BookInfoRepository
import com.sviatkuzbyt.library.data.repositories.loadCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel(private val id: Long, private val application: Application): AndroidViewModel(application) {
    val label = MutableLiveData<String>()
    val image = MutableLiveData<Bitmap>()
    val infoList = MutableLiveData<List<LabelData>>()
    val description = MutableLiveData<String>()

    init { loadData() }

    private fun loadData() = viewModelScope.launch(Dispatchers.IO){
        val repository = BookInfoRepository(application)
        repository.loadBook(id)

        withContext(Dispatchers.Main){
            label.postValue(repository.getLabel())
            image.postValue(repository.getImage())
            infoList.postValue(repository.getInfoList())
            description.postValue(repository.getDescription())
        }
    }

    fun getId() = id

    companion object {
        fun factory(id: Long, application: Application): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                BookViewModel(id, application)
            }
        }
    }
}