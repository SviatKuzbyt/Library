package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.database.entity.User
import com.sviatkuzbyt.library.data.other.CurrentUserManager

class ChangeAccountRepository(private val context: Context) {
    private val dao = DatabaseManager.getDao(context)

    suspend fun getAccountData() = dao.getUser(CurrentUserManager.getUser(context))

    suspend fun updateUser(phone: String, password: String, name: String, address: String, plan: Int){
        val user = User(
            CurrentUserManager.getUser(context),
            name,
            phone,
            password,
            address,
            plan
            )

        dao.updateUser(user)
        DatabaseManager.changeAccountSetting = true
    }
}