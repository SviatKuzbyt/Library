package com.sviatkuzbyt.library.ui.elements.recycleradapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.other.BookRecycler
import com.sviatkuzbyt.library.data.other.LabelData
import com.sviatkuzbyt.library.ui.book.info.BookActivity


class LabelDataAdapter(private val dataSet: List<LabelData>, private val context: Context) :
    RecyclerView.Adapter<LabelDataAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.labelLD)
        val data: TextView = view.findViewById(R.id.dataLD)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.label_data_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.label.text = context.getString(dataSet[position].label)
        viewHolder.data.text = dataSet[position].data
    }

    override fun getItemCount() = dataSet.size
}