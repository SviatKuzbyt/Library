package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.CurrentUserManager
import com.sviatkuzbyt.library.data.other.LabelData
import com.sviatkuzbyt.library.data.other.getPlanFormat

class SettingRepository(private val context: Context) {
    private val dao = DatabaseManager.getDao(context)

    suspend fun getAccountData(): List<LabelData>{
        val data = dao.getUserData(CurrentUserManager.getUser(context))
        return listOf(
            LabelData(R.string.name, data.name),
            LabelData(R.string.phone, data.number),
            LabelData(R.string.address, data.address),
            LabelData(R.string.plan, "${getPlanFormat(data.plan)} днів")
        )
    }


}