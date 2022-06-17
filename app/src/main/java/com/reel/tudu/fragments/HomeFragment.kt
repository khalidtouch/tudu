package com.reel.tudu.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.reel.tudu.R
import com.reel.tudu.adapters.HomeRecyclerViewAdapter
import com.reel.tudu.databinding.FragmentHomeBinding
import com.reel.tudu.entities.TodoItem
import com.reel.tudu.viewmodels.HomeViewModel
import com.reel.tudu.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home), AlertDialogFunctionality {

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        val homeAdapter = HomeRecyclerViewAdapter()

        binding?.AddTodoFab?.setOnClickListener {
//            showAddTodoDialogFragment()
            showAddTodoDialog(requireContext())
        }

        binding?.HomeFragmentRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }
        homeViewModel.getAllTodoItems().observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)
        }
    }

    private fun showAddTodoDialogFragment() {
        AddTodoDialogFragment().show(
            childFragmentManager,
            AddTodoDialogFragment.TAG
        )
    }

    private fun showAddTodoDialog(context: Context) {
        val layout = layoutInflater.inflate(
            R.layout.layout_add_todo_dialog,
            null
        )

        val dialogBuilder = AlertDialog.Builder(context)
            .setTitle("Do something new")
            .setView(layout)
            .setPositiveButton(
                "Save"
            ) { _, _ -> onPositiveClickDialog(layout) }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onPositiveClickDialog(view: View?) {
//        val textField = view?.findViewById<TextInputEditText>(R.id.IWantToTextField)
//        homeViewModel.saveTodoItem( //save the item
//            TodoItem(
//                task = textField?.text.toString().trim(),
//            )
//        )
    }

    override fun onNegativeClickDialog(view: View?) {
        TODO("Not yet implemented")
    }

}

interface AlertDialogFunctionality {
    fun onPositiveClickDialog(view: View?)
    fun onNegativeClickDialog(view: View?)
}