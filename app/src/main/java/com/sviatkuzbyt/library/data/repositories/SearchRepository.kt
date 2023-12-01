package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.data.other.formatList

class SearchRepository(private val context: Context) {
    private val dao = DatabaseManager.getDao(context)

    fun loadBooks(name: String): List<BookRecycler>{
        val nameTrim = name.trim()
        return if(nameTrim.isNotEmpty()){
            val list = dao.getBooksByName("%$nameTrim%")
            formatList(context, list)
        }
        else emptyList()

    }
}