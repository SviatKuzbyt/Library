package com.sviatkuzbyt.library.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val imageId: String,
    val author: String,
    val categories: String,
    val pages: Int,
    val description: String
)
