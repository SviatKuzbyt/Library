package com.sviatkuzbyt.library.ui.elements.recycleradapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.other.BookRecycler


class BookAdapter(private val dataSet: List<BookRecycler>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImage: ImageView = view.findViewById(R.id.bookImage)
        val bookName: TextView = view.findViewById(R.id.bookName)
        val authorBook: TextView = view.findViewById(R.id.authorBook)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.book_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bookName.text = dataSet[position].name
        viewHolder.authorBook.text = dataSet[position].author

        val image = dataSet[position].image
        if (image != null)
            viewHolder.bookImage.setImageBitmap(image)
        else
            viewHolder.bookImage.setImageResource(R.drawable.no_image)
    }

    override fun getItemCount() = dataSet.size

}