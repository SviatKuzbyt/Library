package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.CurrentUserManager

class LoginRepository(private val context: Context) {
    private val dao = DatabaseManager.getDao(context)

    suspend fun login(phone: String, password: String): Long{
        val id = dao.getUserId(phone, password)

        if(id != null){
            CurrentUserManager(context).changeUser(id)
            return id
        } else
            throw Exception()
    }
}