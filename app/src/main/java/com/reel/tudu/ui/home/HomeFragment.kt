package com.reel.tudu.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.reel.tudu.InitApp
import com.reel.tudu.R
import com.reel.tudu.adapters.HomeRecyclerViewAdapter
import com.reel.tudu.databinding.FragmentHomeBinding
import com.reel.tudu.ui.addnew.AddTodoBottomSheet

class HomeFragment : Fragment(R.layout.fragment_home), AlertDialogFunctionality {

    private val getApplicationComponent = InitApp().applicationComponent

    private val viewModel: HomeViewModel by viewModels {
        getApplicationComponent.homeViewModelFactory()
    }

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        val homeAdapter = HomeRecyclerViewAdapter()

        binding?.AddTodoFab?.setOnClickListener {
            showAddTodoDialogFragment()

        }

        binding?.HomeFragmentRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }
        viewModel.getAllTodoItems().observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)
        }
    }

    private fun showAddTodoDialogFragment() {
        AddTodoBottomSheet().show(
            requireActivity().supportFragmentManager,
            AddTodoBottomSheet.TAG
        )
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onPositiveClickDialog(view: View?) {
    }

    override fun onNegativeClickDialog(view: View?) {
        TODO("Not yet implemented")
    }

}

interface AlertDialogFunctionality {
    fun onPositiveClickDialog(view: View?)
    fun onNegativeClickDialog(view: View?)
}