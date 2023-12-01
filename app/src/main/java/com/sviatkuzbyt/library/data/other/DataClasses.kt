package com.sviatkuzbyt.library.data.other

import android.graphics.Bitmap

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