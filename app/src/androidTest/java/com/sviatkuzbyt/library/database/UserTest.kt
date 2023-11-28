package com.sviatkuzbyt.library.database

import androidx.test.platform.app.InstrumentationRegistry
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.database.LibraryDao
import com.sviatkuzbyt.library.data.database.entity.User
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserTest {
    private lateinit var libraryDao: LibraryDao
    val user = User(0, "Sviat", "0634678932", "2eweer31", "Lviv NP 123", 1)

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        libraryDao = DatabaseManager.getDao(context)
    }

    @Test
    fun checkWork() = runTest {
        add()
        getId()
    }
    private fun add() {
        libraryDao.addUser(user)
    }

    private fun getId() {
        val id = libraryDao.getUserId(user.number, user.password)
        Assert.assertEquals(1, id)
    }
}