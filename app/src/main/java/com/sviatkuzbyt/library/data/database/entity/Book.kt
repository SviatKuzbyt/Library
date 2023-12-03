package com.sviatkuzbyt.library.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val bookId: Long,
    val name: String,
    val imageId: String,
    val author: String,
    val categories: String,
    val pages: Int,
    val description: String
)
