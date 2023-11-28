package com.sviatkuzbyt.library.data.database

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    private var taskDataBase: LibraryRoom? = null

    fun getDao(context: Context): LibraryDao {
        return getDatabase(context).libraryDao()
    }

    private fun getDatabase(context: Context): LibraryRoom {
        if (taskDataBase == null) {
            synchronized(DatabaseManager::class.java) {
                if (taskDataBase == null) {
                    taskDataBase = Room.databaseBuilder(
                        context.applicationContext,
                        LibraryRoom::class.java, "library_database"
                    ).build()
                }
            }
        }
        return taskDataBase!!
    }
}