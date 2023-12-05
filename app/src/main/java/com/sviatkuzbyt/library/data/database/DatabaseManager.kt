package com.sviatkuzbyt.library.data.database

import android.content.Context
import androidx.room.Room

sealed class ChangeRentTable{
    data object Change: ChangeRentTable()
    data object NoChange: ChangeRentTable()
    data object NotRentList: ChangeRentTable()
}

object DatabaseManager {
    private var taskDataBase: LibraryRoom? = null
    var changeRentTable: ChangeRentTable = ChangeRentTable.NotRentList

    fun setChangeRent(){
        if(changeRentTable != ChangeRentTable.NotRentList)
            changeRentTable = ChangeRentTable.Change
    }

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
                    ).createFromAsset("library.db").build()
                }
            }
        }
        return taskDataBase!!
    }
}