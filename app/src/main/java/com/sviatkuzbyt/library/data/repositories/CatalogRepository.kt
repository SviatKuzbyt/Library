package com.sviatkuzbyt.library.data.repositories

import android.content.Context
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.data.other.formatList
import kotlin.random.Random

class CatalogRepository(private val context: Context) {
    private val dao = DatabaseManager.getDao(context)
    private val recommendationSize = 4

    private val categoryList = listOf(
        "Роман",
        "Фантастика",
        "Сатира",
        "Дистопія",
        "Детектив",
        "Кримінал",
        "Пригоди",
        "Філософська казка",
        "Література для дітей",
        "Наукова фантастика",
        "Епічна фантастика",
        "Фентезі",
        "Антиутопія",
        "Трагедія",
        "Романс")

    fun getCategoryList() = categoryList

    fun getRecommendation(): List<BookRecycler>{
        val booksId = getRandomNonRepeatingNumbers()
        val listWithoutImg = booksId.map {
            dao.getRecyclerBook(it)
        }
        return formatList(context, listWithoutImg)
    }
    private fun getRandomNonRepeatingNumbers(): List<Long> {
        val numbersSet = mutableSetOf<Long>()
        while (numbersSet.size < recommendationSize) {
            val randomNumber = Random.nextLong(dao.getBooksCount()) + 1
            numbersSet.add(randomNumber)
        }
        return numbersSet.toList()
    }
}