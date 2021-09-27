package com.apripachkin.tuneinbrowser.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController
        get() = (
            supportFragmentManager.findFragmentById(R.id.main_activity_fragment_container)
                as NavHostFragment
            ).navController
    private val toolbarVisibleItems = listOf(R.id.detailFragment, R.id.audioFragment)
    private val viewModel: MainActivityViewModel by viewModels()

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
            binding.toolbar.toolbarBackButton.isVisible = destination.id in toolbarVisibleItems
        }
        lifecycleScope.launchWhenStarted {
            viewModel.titleFlow.collect {
                binding.toolbar.toolbarTitle.text = it
            }
        }
    }
}
