package com.reel.tudu.ui.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reel.tudu.MainActivity
import com.reel.tudu.R
import com.reel.tudu.adapters.HomeRecyclerViewAdapter
import com.reel.tudu.databinding.FragmentHomeBinding

import com.reel.tudu.ui.addnew.AddTodoBottomSheet
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    lateinit var homeFragmentComponent: HomeFragmentComponent
    lateinit var viewModel: HomeViewModel
    lateinit var bottomSheet: AddTodoBottomSheet
    lateinit var homeAdapter: HomeRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeFragmentComponent =
            (activity as MainActivity).applicationComponent.homeFragmentComponent().create()
        homeFragmentComponent.inject(this)
        viewModel =
            ViewModelProvider(activity as MainActivity, viewModelFactory)[HomeViewModel::class.java]

    }

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        homeAdapter = HomeRecyclerViewAdapter { todoItem ->
            val completedTask = todoItem
            completedTask.isCompleted = 1
            viewModel.saveTodoItem(completedTask)

            Handler(Looper.getMainLooper()).postDelayed({
                Runnable {
                    populateTaskData()
                }.run()
            }, 400)
        }

        bottomSheet = AddTodoBottomSheet()

        binding?.AddTodoFab?.setOnClickListener {
            showAddTodoDialogFragment()

        }

        binding?.HomeFragmentRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }

        populateTaskData()

    }

    private fun populateTaskData() {

        viewModel.getAllTodoItems().observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)
            binding?.HomeFragmentRecyclerView?.visibility =
                if (it.isNotEmpty()) View.VISIBLE else View.GONE
            binding?.NoTaskStatement?.visibility =
                if (it.isNotEmpty()) View.GONE else View.VISIBLE

        }

        Log.i(TAG, "populateTaskData: calleed")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: Called")
    }

//    override fun onResume() {
//        super.onResume()
//        Log.i(TAG, "onResume: called ")
//        if (isDirty) {
//            viewModel =
//                ViewModelProvider(
//                    activity as MainActivity,
//                    viewModelFactory
//                )[HomeViewModel::class.java]
//            populateTaskData()
//            Log.i(TAG, "onResume: called while dirty")
//        }
//
//    }


    private fun showAddTodoDialogFragment() {
//        bottomSheet.show(
//            requireActivity().supportFragmentManager,
//            AddTodoBottomSheet.TAG
//        )

        findNavController().navigate(R.id.action_homeFragment_to_addTodoBottomSheet)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        val TAG = "HomeFragment"
    }


    private fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.TodoCheckbox -> {
                    if (checked) {

                    }
                }
            }
        }
    }

}

