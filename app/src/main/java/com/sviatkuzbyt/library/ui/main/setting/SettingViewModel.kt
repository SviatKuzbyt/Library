package com.sviatkuzbyt.library.ui.main.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.LabelData
import com.sviatkuzbyt.library.data.other.RentBookRecycler
import com.sviatkuzbyt.library.data.repositories.ReadingRepository
import com.sviatkuzbyt.library.data.repositories.SettingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingViewModel(application: Application): AndroidViewModel(application) {
    private val repository = SettingRepository(application)
    val accountDataList = MutableLiveData<List<LabelData>>()
    val error = MutableLiveData<Int>()

    init {
        loadUserData()
    }

    fun loadUserData() = viewModelScope.launch(Dispatchers.IO){
        try {
            val data = repository.getAccountData()
            withContext(Dispatchers.Main){
                DatabaseManager.changeAccountSetting = false
                accountDataList.postValue(data)
            }
        } catch (_: Exception){
            error.postValue(R.string.error)
        }
    }
}