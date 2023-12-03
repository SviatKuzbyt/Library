package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.database.entity.RentBook
import com.sviatkuzbyt.library.data.other.CurrentUserManager
import com.sviatkuzbyt.library.data.other.LabelData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReturnBookRepository(private val context: Context) {
    private val dao = DatabaseManager.getDao(context)

    suspend fun getReturnData(bookName: String): List<LabelData>{
        val userName = dao.getUserName(CurrentUserManager.getUser(context))

        return listOf(
            LabelData(R.string.book, bookName),
            LabelData(R.string.customer, userName)
        )
    }

    fun makeReturn(rentId: Long){
        dao.deleteRent(rentId)
    }
}