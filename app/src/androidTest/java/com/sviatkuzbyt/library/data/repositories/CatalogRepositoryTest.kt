package com.sviatkuzbyt.library.data.repositories

import androidx.test.platform.app.InstrumentationRegistry
import com.sviatkuzbyt.library.data.other.BookRecycler
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CatalogRepositoryTest{
    private lateinit var repository: CatalogRepository
    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        repository = CatalogRepository(context)
    }

    @Test
    fun checkGetRecommendation(){
        val list = repository.getRecommendation()
        Assert.assertNotEquals(list, emptyList<BookRecycler>())
        println("RESULT: $list")
    }

}