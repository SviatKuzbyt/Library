package com.sviatkuzbyt.library.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sviatkuzbyt.library.data.database.entity.Book
import com.sviatkuzbyt.library.data.database.entity.RentBook
import com.sviatkuzbyt.library.data.database.entity.User
import com.sviatkuzbyt.library.data.other.BookRecyclerWithoutImg
import com.sviatkuzbyt.library.data.other.RentBookRecyclerWithoutImg
import com.sviatkuzbyt.library.data.other.UserRentData

@Dao
interface LibraryDao {
    @Query("SELECT userId FROM User WHERE number=:number AND password=:password")
    fun getUserId(number: String, password: String): Long?

    @Query("SELECT 1 FROM User WHERE number=:number")
    fun checkNumber(number: String): Int?

    @Query("SELECT * FROM User")
    fun getAll(): List<User>?

    @Query("SELECT name, address, `plan` FROM User WHERE userId=:id")
    fun getUserRentData(id: Long): UserRentData?

    @Insert
    fun addUser(user: User)

    @Query("SELECT name FROM User WHERE userId=:id")
    fun getUserName(id: Long): String

    @Query("SELECT COUNT(*) FROM Book")
    fun getBooksCount(): Long

    @Query("SELECT bookId, imageId, name, author FROM Book WHERE bookId=:id LIMIT 1")
    fun getRecyclerBook(id: Long): BookRecyclerWithoutImg

    @Query("SELECT bookId, imageId, name, author FROM Book WHERE categories LIKE :category")
    fun getBooksByCategory(category: String): List<BookRecyclerWithoutImg>

    @Query("SELECT bookId, imageId, name, author FROM Book WHERE name LIKE :name")
    fun getBooksByName(name: String): List<BookRecyclerWithoutImg>

    @Query("SELECT * FROM Book WHERE bookId=:id LIMIT 1")
    fun getBook(id: Long): Book

    @Insert
    fun addRentBook(book: RentBook)

    @Query("SELECT Book.bookId, Book.name, Book.imageId, Book.author, RentBook.rentEndDate, RentBook.rentId FROM Book INNER JOIN RentBook ON Book.bookId = RentBook.bookId WHERE RentBook.userId = :userId")
    fun getRentBooks(userId: Long): List<RentBookRecyclerWithoutImg>

    @Query("DELETE FROM rentbook WHERE rentId=:id")
    fun deleteRent(id: Long)
}