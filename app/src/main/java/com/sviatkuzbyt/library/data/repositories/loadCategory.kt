package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.data.other.formatList

fun loadCategory(context: Context, category: String): List<BookRecycler>{
    val dao = DatabaseManager.getDao(context)
    val list = dao.getBooksByCategory("%$category%")
    return formatList(context, list)
}