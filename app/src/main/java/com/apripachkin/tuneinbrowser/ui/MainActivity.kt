package com.apripachkin.tuneinbrowser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TuneInLight)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun updateTitle(title: String) {
        binding.toolbar.toolbarTitle.text = title
    }
}