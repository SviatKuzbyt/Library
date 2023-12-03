package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.CurrentUserManager
import com.sviatkuzbyt.library.data.other.RentBookRecycler
import com.sviatkuzbyt.library.data.other.formatListRent
import com.sviatkuzbyt.library.data.other.getLowBitmapFromAsset

class ReadingRepository(private val context: Context) {
    val dao = DatabaseManager.getDao(context)

    suspend fun loadList() =
        formatListRent(context, dao.getRentBooks(CurrentUserManager.getUser(context)))
}