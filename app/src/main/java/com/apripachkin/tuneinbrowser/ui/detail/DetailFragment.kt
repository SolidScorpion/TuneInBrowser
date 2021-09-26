package com.apripachkin.tuneinbrowser.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.databinding.DetailFragmentBinding
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.domain.models.AudioItem
import com.apripachkin.tuneinbrowser.domain.models.HeaderItem
import com.apripachkin.tuneinbrowser.domain.models.LinkItem
import com.apripachkin.tuneinbrowser.ui.ImageLoader
import com.apripachkin.tuneinbrowser.ui.MainActivity
import com.apripachkin.tuneinbrowser.ui.audio.AudioFragment
import com.apripachkin.tuneinbrowser.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {
    private val binding by viewBinding(DetailFragmentBinding::bind)
    private val viewModel: DetailsViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val link = arguments?.getString(LINK) ?: error("Did not receive argument")
        viewModel.loadData(link)
        binding.detailsRefresh.setOnRefreshListener {
            viewModel.loadData(link)
        }
        val outLineItemAdapter = OutLineItemAdapter(imageLoader = imageLoader) {
            Timber.d("Clicked $it")
            if (it is AudioItem) {
                findNavController(this).navigate(
                    R.id.action_detailFragment_to_audioFragment, bundleOf(
                        AudioFragment.AUDIO_LINK to it
                    )
                )
            } else {
                val url = when (it) {
                    is LinkItem -> it.url
                    is HeaderItem -> it.moreItemsLink
                    else -> null
                }
                url?.also { link ->
                    findNavController(this).navigate(
                        R.id.action_global_detailFragment, bundleOf(
                            LINK to link
                        )
                    )
                }
            }
        }
        outLineItemAdapter.setHasStableIds(true)
        binding.detailsRv.adapter = outLineItemAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect {
                    when (it) {
                        Fail -> {
                            stopSwipeRefresh()
                            Timber.d("Failed")
                        }
                        Loading -> {
                            if (!binding.detailsRefresh.isRefreshing) {
                                binding.detailsRefresh.isRefreshing = true
                            }
                        }
                        is Success -> {
                            stopSwipeRefresh()
                            val title = it.value.title ?: ""
                            (requireActivity() as MainActivity).updateTitle(title)
                            outLineItemAdapter.updateData(it.value.listItems)
                        }
                    }
                }
            }
        }

    }
    private fun stopSwipeRefresh() {
        if (binding.detailsRefresh.isRefreshing) {
            binding.detailsRefresh.isRefreshing = false
        }
    }

    companion object {
        const val LINK = "link"
    }
}