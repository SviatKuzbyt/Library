package com.sviatkuzbyt.library.data.other

import android.graphics.Bitmap
import java.net.Inet4Address

data class BookRecycler(
    val id: Long,
    val image: Bitmap?,
    val name: String,
    val author: String
)

data class BookRecyclerWithoutImg(
    val id: Long,
    val imageId: String,
    val name: String,
    val author: String
)

data class LabelData(
    val label: Int,
    val data: String
)

data class UserRentData(
    val name: String,
    val address: String,
    val plan: Int
)