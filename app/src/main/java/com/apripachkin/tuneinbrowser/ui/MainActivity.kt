package com.apripachkin.tuneinbrowser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.databinding.ActivityMainBinding
import com.apripachkin.tuneinbrowser.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController
        get() = (supportFragmentManager.findFragmentById(R.id.main_activity_fragment_container)
                as NavHostFragment).navController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TuneInLight)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainActivityFragmentContainer
        binding.toolbar.toolbarBackButton.setOnClickListener {
            navController.navigateUp()
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.toolbarBackButton.isVisible = destination.label == DetailFragment::class.java.simpleName
        }
    }

    fun updateTitle(title: String) {
        binding.toolbar.toolbarTitle.text = title
    }
}