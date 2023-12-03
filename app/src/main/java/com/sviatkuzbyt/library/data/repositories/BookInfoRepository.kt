package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.database.entity.Book
import com.sviatkuzbyt.library.data.other.LabelData
import com.sviatkuzbyt.library.data.other.getBitmapFromAsset

class BookInfoRepository(private val context: Context) {
    private val dao = DatabaseManager.getDao(context)
    private lateinit var book: Book

    fun loadBook(id: Long){
        book = dao.getBook(id)
    }

    fun getLabel() = book.name

    fun getInfoList() = listOf(
        LabelData(R.string.author, book.author),
        LabelData(R.string.kind, book.categories),
        LabelData(R.string.pages, book.pages.toString()),
    )

    fun getDescription() = book.description

    fun getImage() = getBitmapFromAsset(context, book.imageId)
}