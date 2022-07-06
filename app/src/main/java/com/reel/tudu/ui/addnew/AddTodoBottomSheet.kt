package com.reel.tudu.ui.addnew

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.reel.tudu.MainActivity
import com.reel.tudu.R
import com.reel.tudu.entities.TodoItem
import com.reel.tudu.ui.home.HomeFragment
import com.reel.tudu.ui.home.HomeViewModel
import com.reel.tudu.ui.home.HomeViewModelFactory
import javax.inject.Inject

class AddTodoBottomSheet : BottomSheetDialogFragment(), OnEditTodoItemListener {


    @Inject
    lateinit var addNewViewModelFactory: AddNewViewModelFactory

    lateinit var addNewViewModel: AddNewViewModel
    private lateinit var addNewCancelBtn: MaterialButton
    private lateinit var addNewSaveBtn: MaterialButton
    private lateinit var addNewEditText: TextInputEditText

    private lateinit var parentFragment: HomeFragment

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).applicationComponent.homeFragmentComponent().create()
            .inject(this)

        addNewViewModel = ViewModelProvider(
            activity as MainActivity,
            addNewViewModelFactory
        )[AddNewViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_add_todo_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragment = HomeFragment()
        bindViews(view)
        handleEvents()
    }


    private fun handleEvents() {
        addNewEditText.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            addNewViewModel.cacheTodoItem(TodoItem(task = text.toString()))
        })

        addNewCancelBtn.setOnClickListener { onAbort() }

        addNewSaveBtn.setOnClickListener { onSave() }
    }


    private fun bindViews(view: View) {
        addNewCancelBtn = view.findViewById(R.id.AddNewCancelButton)
        addNewEditText = view.findViewById(R.id.AddNewEditText)
        addNewSaveBtn = view.findViewById(R.id.AddNewSaveButton)
    }

    companion object {
        val TAG = "AddTodoDialogFragment"
    }

    override fun onSave() {
        addNewViewModel.todoItemLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                addNewViewModel.saveTodoItem(it)
            }
        }
        addNewViewModel.clearTaskData()
        this.dismiss()
        findNavController().navigate(R.id.action_addTodoBottomSheet_to_homeFragment)
        Log.i(TAG, "onSave: Task saved")
    }

    override fun onTextChange() {
        TODO("Not yet implemented")
    }

    override fun onAbort() {
        this.dismiss()
        findNavController().navigate(R.id.action_addTodoBottomSheet_to_homeFragment)

    }
}


interface OnEditTodoItemListener {
    fun onSave()
    fun onTextChange()
    fun onAbort()
}