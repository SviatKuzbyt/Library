package com.sviatkuzbyt.library.ui.main.catalog.search

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.databinding.ActivityCategoryBinding
import com.sviatkuzbyt.library.databinding.ActivitySearchBinding
import com.sviatkuzbyt.library.ui.elements.recycleradapters.SearchAdapter

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextSearch.requestFocus()

        binding.backSearchButton.setOnClickListener { finishAfterTransition() }

        binding.recyclerSearch.layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter()
        binding.recyclerSearch.adapter = adapter

        viewModel.list.observe(this){
            adapter.addElements(it)
        }

        binding.editTextSearch.setOnEditorActionListener { view, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.search(view.text.toString())
                    hideKeyboard()
                    true
                }
                else -> false
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}