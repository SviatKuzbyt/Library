package com.sviatkuzbyt.library.ui.main.catalog.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.databinding.ActivityCategoryBinding
import com.sviatkuzbyt.library.ui.elements.recycleradapters.BookAdapter

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("category") ?: "Error"

        val viewModel: CategoryViewModel
                by viewModels{CategoryViewModel.factory(this.application, category)}

        setSupportActionBar(binding.toolbarCategory).apply {
            title = category
        }
        binding.toolbarCategory.setNavigationOnClickListener {
            finish()
        }

        binding.recyclerCategory.layoutManager = LinearLayoutManager(this)

        viewModel.list.observe(this){
            binding.recyclerCategory.adapter = BookAdapter(it, this)
        }
    }
}