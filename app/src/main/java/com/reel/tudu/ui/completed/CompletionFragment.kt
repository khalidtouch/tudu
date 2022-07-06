package com.reel.tudu.ui.completed

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reel.tudu.MainActivity
import com.reel.tudu.R
import com.reel.tudu.adapters.CompletedTaskRecyclerViewAdapter
import com.reel.tudu.databinding.FragmentCompletionBinding
import com.reel.tudu.ui.home.HomeViewModel
import com.reel.tudu.ui.home.HomeViewModelFactory
import javax.inject.Inject

class CompletionFragment : Fragment(R.layout.fragment_completion) {
    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    lateinit var viewModel: HomeViewModel
    lateinit var completedAdapter: CompletedTaskRecyclerViewAdapter

    private var _binding: FragmentCompletionBinding? = null
    val binding get() = _binding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).applicationComponent.inject(this)
        viewModel =
            ViewModelProvider(activity as MainActivity, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCompletionBinding.bind(view)
        completedAdapter = CompletedTaskRecyclerViewAdapter()

        binding?.CompletedTaskRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = completedAdapter
        }

        populateCompletedTaskData()
    }

    private fun populateCompletedTaskData() {
        viewModel.getAllCompletedTasks().observe(viewLifecycleOwner){
            completedAdapter.submitList(it)
        }
        Log.i(TAG, "populateCompletedTaskData: Task added to completed")
    }

    companion object {
        val TAG = "CompletedTaskFragment"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}