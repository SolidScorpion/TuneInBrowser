package com.apripachkin.tuneinbrowser.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.databinding.HomeFragmentBinding
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.ui.MainActivityViewModel
import com.apripachkin.tuneinbrowser.ui.detail.DetailFragment
import com.apripachkin.tuneinbrowser.utils.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private val binding: HomeFragmentBinding by viewBinding(HomeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.updateTitle(getString(R.string.home_screen_title))
        binding.homeCategoryPager.adapter = CategoryAdapter {
            Timber.d("Clicked on $it")
            findNavController(this).navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundleOf(DetailFragment.LINK to it.url)
            )
        }
        binding.homeCategoryPager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(binding.tabLayout, binding.homeCategoryPager) { _, _ -> }.attach()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect {
                    when (it) {
                        Fail -> binding.progressCircleIndeterminate.hide()
                        Loading -> binding.progressCircleIndeterminate.show()
                        is Success -> {
                            (binding.homeCategoryPager.adapter as CategoryAdapter).updateItems(it.value)
                            binding.progressCircleIndeterminate.hide()
                        }
                    }
                }
            }
        }
    }
}
