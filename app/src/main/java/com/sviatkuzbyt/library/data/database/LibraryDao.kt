package com.sviatkuzbyt.library.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sviatkuzbyt.library.data.database.entity.Book
import com.sviatkuzbyt.library.data.database.entity.User
import com.sviatkuzbyt.library.data.other.BookRecyclerWithoutImg

@Dao
interface LibraryDao {
    @Query("SELECT id FROM User WHERE number=:number AND password=:password")
    fun getUserId(number: String, password: String): Int?

    @Query("SELECT 1 FROM User WHERE number=:number")
    fun checkNumber(number: String): Int?

    @Query("SELECT * FROM User")
    fun getAll(): List<User>?

    @Insert
    fun addUser(user: User)

    @Query("SELECT COUNT(*) FROM Book")
    fun getBooksCount(): Long

    @Query("SELECT id, imageId, name, author FROM Book WHERE id=:id LIMIT 1")
    fun getRecyclerBook(id: Long): BookRecyclerWithoutImg

    @Query("SELECT id, imageId, name, author FROM Book WHERE categories LIKE :category")
    fun getBooksByCategory(category: String): List<BookRecyclerWithoutImg>

    @Query("SELECT id, imageId, name, author FROM Book WHERE name LIKE :name")
    fun getBooksByName(name: String): List<BookRecyclerWithoutImg>

    @Query("SELECT * FROM Book WHERE id=:id LIMIT 1")
    fun getBook(id: Long): Book
}