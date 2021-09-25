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
import com.apripachkin.tuneinbrowser.data.AudioOutLine
import com.apripachkin.tuneinbrowser.data.HeaderOutLine
import com.apripachkin.tuneinbrowser.data.LinkOutLine
import com.apripachkin.tuneinbrowser.data.TextOutLine
import com.apripachkin.tuneinbrowser.databinding.DetailFragmentBinding
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.ui.ImageLoader
import com.apripachkin.tuneinbrowser.ui.MainActivity
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
        val outLineItemAdapter = OutLineItemAdapter(imageLoader = imageLoader) {
            Timber.d("Clicked $it")
            val url = when (it) {
                is AudioOutLine -> it.URL
                is LinkOutLine -> it.URL
                is HeaderOutLine, is TextOutLine -> null
            }
            url?.also { link ->
                findNavController(this).navigate(
                    R.id.action_global_detailFragment, bundleOf(
                        LINK to link
                    )
                )
            }
        }
        binding.detailsRv.adapter = outLineItemAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect {
                    when (it) {
                        Fail -> Timber.d("Failed")
                        Loading -> Timber.d("Loading")
                        is Success -> {
                            val title = it.value.head.title ?: ""
                            (requireActivity() as MainActivity).updateTitle(title)
                            outLineItemAdapter.updateData(it.value.body)
                        }
                    }
                }
            }
        }

    }

    companion object {
        const val LINK = "link"
    }
}