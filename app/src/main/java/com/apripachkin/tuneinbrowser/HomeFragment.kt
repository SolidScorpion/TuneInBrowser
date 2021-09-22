package com.apripachkin.tuneinbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.apripachkin.tuneinbrowser.databinding.HomeFragmentBinding
import com.apripachkin.tuneinbrowser.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding: HomeFragmentBinding by viewBinding(HomeFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tv.setOnClickListener {
            viewModel.request()
        }
    }

}