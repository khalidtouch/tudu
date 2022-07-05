package com.reel.tudu.ui.addnew

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.reel.tudu.InitApp
import com.reel.tudu.R
import com.reel.tudu.entities.TodoItem

class AddTodoBottomSheet : BottomSheetDialogFragment(), OnEditTodoItemListener {




    private lateinit var addNewCancelBtn: MaterialButton
    private lateinit var addNewSaveBtn: MaterialButton
    private lateinit var addNewEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_add_todo_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        handleEvents()
    }

    private fun handleEvents() {
        addNewEditText.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            viewModel.cacheTodoItem(TodoItem(task = text.toString()))
        })

        viewModel.getCachedTodoItem().observe(viewLifecycleOwner) { todoItem ->
            addNewSaveBtn.setOnClickListener { onSave(todoItem) }
        }

        addNewCancelBtn.setOnClickListener { onAbort() }
    }

    private fun bindViews(view: View) {
        addNewCancelBtn = view.findViewById(R.id.AddNewCancelButton)
        addNewEditText = view.findViewById(R.id.AddNewEditText)
        addNewSaveBtn = view.findViewById(R.id.AddNewSaveButton)
    }

    companion object {
        val TAG = "AddTodoDialogFragment"
    }

    override fun onSave(todoItem: TodoItem?) {
        if (todoItem != null) viewModel.saveTodoItem(todoItem)
        this.dismiss()
    }

    override fun onTextChange() {
        TODO("Not yet implemented")
    }

    override fun onAbort() {
        this.dismiss()
    }
}


interface OnEditTodoItemListener {
    fun onSave(todoItem: TodoItem?)
    fun onTextChange()
    fun onAbort()
}