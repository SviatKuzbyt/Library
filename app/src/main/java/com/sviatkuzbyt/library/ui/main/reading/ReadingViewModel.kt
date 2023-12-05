package com.sviatkuzbyt.library.ui.main.reading

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.ChangeRentTable
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.RentBookRecycler
import com.sviatkuzbyt.library.data.repositories.ReadingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReadingViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ReadingRepository(application)
    val list = MutableLiveData<List<RentBookRecycler>>()
    val error = MutableLiveData<Int>()

    init {
        loadList()
    }

    fun loadList() = viewModelScope.launch(Dispatchers.IO){
        try {
            val _list = repository.loadList()
            withContext(Dispatchers.Main){
                DatabaseManager.changeRentTable = ChangeRentTable.NoChange
                list.postValue(_list)
            }
        } catch (_: Exception){
            error.postValue(R.string.error)
        }
    }
}