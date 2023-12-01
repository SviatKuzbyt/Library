package com.sviatkuzbyt.library.ui.elements.recycleradapters

import com.sviatkuzbyt.library.data.other.BookRecycler

class SearchAdapter: BookAdapter(emptyList()) {
    fun addElements(list: List<BookRecycler>){
        if(itemCount > 0)
            notifyItemRangeRemoved(0, itemCount)

        dataSet = list
        notifyItemRangeInserted(0, itemCount)
    }
}