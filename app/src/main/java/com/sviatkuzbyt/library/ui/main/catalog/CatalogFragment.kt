package com.sviatkuzbyt.library.ui.main.catalog

import android.app.ActivityOptions
import android.content.Intent
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
import com.sviatkuzbyt.library.ui.main.catalog.search.SearchActivity

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
            binding.recommendationRecycler.adapter = BookAdapter(it, requireActivity())
        }

        viewModel.categoryList.observe(viewLifecycleOwner){
            binding.categoriesRecycler.adapter = CategoryAdapter(it, requireContext())
        }

        binding.search.setOnClickListener{
            startActivity(
                Intent(requireContext(), SearchActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}