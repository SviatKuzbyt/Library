package com.sviatkuzbyt.library.ui.main.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sviatkuzbyt.library.databinding.FragmentCatalogBinding
import com.sviatkuzbyt.library.ui.elements.recycleradapters.BookAdapter
import com.sviatkuzbyt.library.ui.elements.recycleradapters.CategoryAdapter

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recommendationRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.categoriesRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.recommendationList.observe(viewLifecycleOwner){
            binding.recommendationRecycler.adapter = BookAdapter(it)
        }

        viewModel.categoryList.observe(viewLifecycleOwner){
            binding.categoriesRecycler.adapter = CategoryAdapter(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}