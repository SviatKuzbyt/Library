package com.sviatkuzbyt.library.ui.elements.recycleradapters

import android.app.Activity
import android.content.Context
import com.sviatkuzbyt.library.data.other.BookRecycler

class SearchAdapter(activity: Activity): BookAdapter(emptyList(), activity) {
    fun addElements(list: List<BookRecycler>){
        if(itemCount > 0)
            notifyItemRangeRemoved(0, itemCount)

        dataSet = list
        notifyItemRangeInserted(0, itemCount)
    }
}