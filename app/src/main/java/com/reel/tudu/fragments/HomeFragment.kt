package com.reel.tudu.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.reel.tudu.R
import com.reel.tudu.adapters.HomeRecyclerViewAdapter
import com.reel.tudu.databinding.FragmentHomeBinding
import com.reel.tudu.viewmodels.HomeViewModel
import com.reel.tudu.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        val homeAdapter = HomeRecyclerViewAdapter()
        binding?.HomeFragmentRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }
        homeViewModel.getAllTodoItems().observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}