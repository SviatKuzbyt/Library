package com.sviatkuzbyt.library.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sviatkuzbyt.library.data.database.entity.Book
import com.sviatkuzbyt.library.data.database.entity.RentBook
import com.sviatkuzbyt.library.data.database.entity.User

@Database(entities = [Book::class, User::class, RentBook::class], version = 1, exportSchema = false)
abstract class LibraryRoom : RoomDatabase() {
    abstract fun libraryDao(): LibraryDao
}