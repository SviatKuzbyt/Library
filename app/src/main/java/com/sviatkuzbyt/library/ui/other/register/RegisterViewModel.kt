package com.sviatkuzbyt.library.ui.other.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.library.data.repositories.LoginRepository
import com.sviatkuzbyt.library.data.repositories.RegisterRepository
import com.sviatkuzbyt.library.ui.other.login.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class RegisterResult{
    data object Successful: RegisterResult()
    data object AlreadyRegistered: RegisterResult()
    data object NoData: RegisterResult()
    data object Error: RegisterResult()

}
class RegisterViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RegisterRepository(application)
    val result = MutableLiveData<RegisterResult>()

    fun register(phone: String, password: String, name: String, address: String, plan: Int){
        if(phone.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && address.isNotEmpty()){

            viewModelScope.launch(Dispatchers.IO){
                try {
                    repository.register(phone, password, name, address, plan)
                    result.postValue(RegisterResult.Successful)

                } catch (e: RegisterRepository.AlreadyRegisteredException){
                    result.postValue(RegisterResult.AlreadyRegistered)
                } catch (e: Exception){
                    result.postValue(RegisterResult.Error)
                    Log.e("appLog", e.toString())
                }
            }
        }
        else result.postValue(RegisterResult.NoData)
    }
}