package com.sviatkuzbyt.library.ui.elements.recycleradapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.ui.book.info.BookActivity


open class BookAdapter(protected var dataSet: List<BookRecycler>, private val activity: Activity) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImage: ImageView = view.findViewById(R.id.bookImage)
        val bookName: TextView = view.findViewById(R.id.bookName)
        val authorBook: TextView = view.findViewById(R.id.authorBook)
        val cardView: CardView = view.findViewById(R.id.cardViewBook)

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

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(activity, BookActivity::class.java)
            intent.putExtra("id", dataSet[position].id)
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, viewHolder.cardView, "bookImg")
            activity.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount() = dataSet.size

}