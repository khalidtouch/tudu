package com.reel.tudu.ui.settings

import android.content.Context
import androidx.fragment.app.Fragment
import com.reel.tudu.MainActivity
import com.reel.tudu.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).applicationComponent.inject(this)
    }
}