package com.sviatkuzbyt.library.ui.elements.recycleradapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.ui.main.catalog.category.CategoryActivity

class CategoryAdapter(private val dataSet: List<String>, private val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textOpen: TextView = view.findViewById(R.id.textOpen)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.open_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textOpen.text = dataSet[position]

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("category", dataSet[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}