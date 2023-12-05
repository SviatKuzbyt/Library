package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.database.entity.RentBook
import com.sviatkuzbyt.library.data.other.CurrentUserManager
import com.sviatkuzbyt.library.data.other.LabelData
import com.sviatkuzbyt.library.data.other.getPlanFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MakeOrderRepository(private val context: Context, private val bookName: String, private val bookId: Long) {
    private val dao = DatabaseManager.getDao(context)
    private var dayRent = 7

    suspend fun getOrderData(): List<LabelData>{
        val userData = dao.getUserRentData(CurrentUserManager.getUser(context)) ?: throw Exception()
        dayRent = getPlanFormat(userData.plan)
        return listOf(
            LabelData(R.string.book, bookName),
            LabelData(R.string.customer, userData.name),
            LabelData(R.string.address, userData.address),
            LabelData(R.string.term, "$dayRent днів")
        )
    }

    suspend fun makeOrder(){
        val rentBook = RentBook(0, CurrentUserManager.getUser(context), bookId, createDataRent())
        dao.addRentBook(rentBook)
        DatabaseManager.setChangeRent()
    }

    private fun createDataRent(): String{
        val today = LocalDate.now()
        val futureDate = today.plusDays(dayRent.toLong())
        val formatter = DateTimeFormatter.ofPattern("dd MMM")
        return futureDate.format(formatter)
    }
}