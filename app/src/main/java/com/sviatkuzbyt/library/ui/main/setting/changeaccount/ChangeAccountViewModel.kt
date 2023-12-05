package com.sviatkuzbyt.library.ui.main.setting.changeaccount

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.entity.User
import com.sviatkuzbyt.library.data.repositories.ChangeAccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChangeAccountViewModel(application: Application): AndroidViewModel(application) {
    private var repository = ChangeAccountRepository(application)
    val user = MutableLiveData<User>()
    val successful = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO){
            val _user = repository.getAccountData()
            withContext(Dispatchers.Main){
                user.postValue(_user)
            }
        }
    }

    fun update(phone: String, password: String, name: String, address: String, plan: Int)
        = viewModelScope.launch(Dispatchers.IO){
            try {
                repository.updateUser(phone, password, name, address, plan)
                successful.postValue(true)
            } catch (_: Exception){
                successful.postValue(false)
            }
        }
}