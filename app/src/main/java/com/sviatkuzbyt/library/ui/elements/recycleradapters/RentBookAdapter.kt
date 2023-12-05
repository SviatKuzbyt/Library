package com.sviatkuzbyt.library.ui.elements.recycleradapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.other.RentBookRecycler
import com.sviatkuzbyt.library.ui.book.info.BookActivity
import com.sviatkuzbyt.library.ui.main.reading.returnbook.ReturnBookActivity


open class RentBookAdapter(private val activity: Activity) :
    RecyclerView.Adapter<RentBookAdapter.ViewHolder>() {
        private var dataSet = listOf<RentBookRecycler>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImage: ImageView = view.findViewById(R.id.bookImage)
        val bookName: TextView = view.findViewById(R.id.bookName)
        val authorBook: TextView = view.findViewById(R.id.authorBook)
        val cardView: CardView = view.findViewById(R.id.cardViewBook)

        val textRentTime: TextView = view.findViewById(R.id.textRentTime)
        val returnButton: Button = view.findViewById(R.id.returnButton)
    }

    fun setElements(list: List<RentBookRecycler>){
        if(itemCount > 0)
            notifyItemRangeRemoved(0, itemCount)

        dataSet = list
        notifyItemRangeInserted(0, itemCount)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rent_book_item, viewGroup, false)
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
            openBookActivity(dataSet[position].bookId, viewHolder.cardView)
        }

        val rentDate = "${activity.getString(R.string.return_to)} ${dataSet[position].rentDate}"
        viewHolder.textRentTime.text = rentDate

        viewHolder.returnButton.setOnClickListener {
            openReturnActivity(dataSet[position].rentId, dataSet[position].name)
        }
    }

    private fun openBookActivity(bookId: Long, view: View){
        val intent = Intent(activity, BookActivity::class.java)
        intent.putExtra("id", bookId)
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, view, "bookImg")
        activity.startActivity(intent, options.toBundle())
    }

    private fun openReturnActivity(rentId: Long, name: String){
        val intent = Intent(activity, ReturnBookActivity::class.java).apply {
            putExtra("id", rentId)
            putExtra("name", name)
        }
        activity.startActivity(intent)
    }

    override fun getItemCount() = dataSet.size

}