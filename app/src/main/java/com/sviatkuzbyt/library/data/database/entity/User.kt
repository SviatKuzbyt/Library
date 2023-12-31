package com.sviatkuzbyt.library.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Long,
    val name: String,
    val number: String,
    val password: String,
    val address: String,
    val plan: Int
)