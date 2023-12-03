package com.sviatkuzbyt.library.data.other

import android.content.Context

fun formatList(context: Context, list: List<BookRecyclerWithoutImg>)
= list.map {
    BookRecycler(it.bookId, getLowBitmapFromAsset(context, it.imageId), it.name, it.author)
}

fun formatListRent(context: Context, list: List<RentBookRecyclerWithoutImg>)
        = list.map {
    RentBookRecycler(it.bookId, getLowBitmapFromAsset(context, it.imageId), it.name, it.author, it.rentEndDate, it.rentId)
}