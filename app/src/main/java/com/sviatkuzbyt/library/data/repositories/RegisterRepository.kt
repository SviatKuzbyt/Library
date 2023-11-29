package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.database.entity.User
import com.sviatkuzbyt.library.data.other.CurrentUserManager

class RegisterRepository(context: Context) {
    private val dao = DatabaseManager.getDao(context)

    class AlreadyRegisteredException: Exception()

    fun register(phone: String, password: String, name: String, address: String, plan: Int){
        if (isRegistered(phone)) throw AlreadyRegisteredException()

        val user = User(0, name, phone, password, address, plan)
        dao.addUser(user)
    }

    private fun isRegistered(phone: String) = dao.checkNumber(phone) == 1
}