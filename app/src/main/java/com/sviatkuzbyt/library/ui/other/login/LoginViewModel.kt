package com.sviatkuzbyt.library.ui.other.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.library.data.repositories.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class LoginResult{
    data object Successful: LoginResult()
    data object Failed: LoginResult()
    data object NoData: LoginResult()

}

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val repository = LoginRepository(application)
    val result = MutableLiveData<LoginResult>()

    fun login(phone: String, password: String) {
        if (phone.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    repository.login(phone, password)
                    result.postValue(LoginResult.Successful)
                } catch (e: Exception){
                    result.postValue(LoginResult.Failed)
                }
            }
        }
        else
            result.postValue(LoginResult.NoData)
    }
}