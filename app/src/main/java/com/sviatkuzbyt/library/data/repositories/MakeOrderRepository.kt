package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.CurrentUserManager
import com.sviatkuzbyt.library.data.other.LabelData

class MakeOrderRepository(context: Context, private val bookName: String) {
    private val dao = DatabaseManager.getDao(context)

    fun getOrderData(): List<LabelData>{
        val userData = dao.getUserRentData(CurrentUserManager.getUser()) ?: throw Exception()
        val days = when(userData.plan){
            0 -> "7 днів"
            1 -> "14 днів"
            else -> "Місяць"
        }
        return listOf(
            LabelData(R.string.book, bookName),
            LabelData(R.string.customer, userData.name),
            LabelData(R.string.address, userData.address),
            LabelData(R.string.term, days)
        )
    }
}