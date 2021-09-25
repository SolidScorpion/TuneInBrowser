package com.apripachkin.tuneinbrowser.ui.audio

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.databinding.AudioFragmentBinding
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.domain.models.AudioItem
import com.apripachkin.tuneinbrowser.ui.ImageLoader
import com.apripachkin.tuneinbrowser.utils.viewBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AudioFragment : Fragment(R.layout.audio_fragment) {
    private val binding: AudioFragmentBinding by viewBinding(AudioFragmentBinding::bind)
    private val audioViewModel: AudioViewModel by viewModels()
    @Inject lateinit var imageLoader: ImageLoader

    private var player: SimpleExoPlayer? = null
    private lateinit var audioItem: AudioItem
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        audioItem = arguments?.getParcelable(AUDIO_LINK) ?: error("Audio link is required")
        binding.audioNowPlaying.text = audioItem.subText
        binding.audioTitleTv.text = audioItem.text
        val imageUrl = audioItem.playingImage ?: audioItem.image
        imageLoader.loadImageInto(binding.audioCurrentSongImage, imageUrl)
        audioViewModel.fetchAudioLink(audioItem.url)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                audioViewModel.data.collect {
                    when(it) {
                        Fail -> Timber.d("Failed to load")
                        Loading -> Timber.d("Loading...")
                        is Success -> {
                            player?.playWhenReady = true
                            player?.setMediaItem(MediaItem.fromUri(it.value.url))
                            player?.prepare()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
    }
    private fun initializePlayer() {
        if (player == null) {
            player = SimpleExoPlayer.Builder(requireContext()).build()
            player?.playWhenReady = true
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun releasePlayer() {
        player?.run {
            playWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

    companion object {
        const val AUDIO_LINK = "audio_link"
    }
}