package com.sviatkuzbyt.library.ui.main.reading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.databinding.FragmentReadingBinding
import com.sviatkuzbyt.library.ui.elements.makeToast
import com.sviatkuzbyt.library.ui.elements.recycleradapters.RentBookAdapter


class ReadingFragment : Fragment() {

    private var _binding: FragmentReadingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReadingViewModel by viewModels()
    private lateinit var adapter: RentBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(DatabaseManager.changeRentTable == true)
            viewModel.loadList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rentBookRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = RentBookAdapter(requireActivity())
        binding.rentBookRecycler.adapter = adapter

        viewModel.list.observe(viewLifecycleOwner){
            adapter.setElements(it)

            if(it.isEmpty())
                binding.noBooks.visibility = View.VISIBLE
            else if(binding.noBooks.isVisible)
                binding.noBooks.visibility = View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner){
            makeToast(it, requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}