package com.reel.tudu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.ui.setupWithNavController
import com.reel.tudu.databinding.ActivityMainBinding
import com.reel.tudu.di.component.ApplicationComponent

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationComponent = (application as InitApp).applicationComponent

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.MainNavHostFragment)
                as NavHostFragment
        binding?.MainBottomNavigationView?.setupWithNavController(navHostFragment.navController)

    }
}