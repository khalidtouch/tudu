package com.reel.tudu.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AddTodoDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setMessage("Hello Dear")
            .setPositiveButton("Save") { _, _ -> }
        Log.i(TAG, "onCreateDialog: Dialog created")
        return dialogBuilder.create()
    }

    companion object {
        val TAG = "AddTodoDialogFragment"
    }
}