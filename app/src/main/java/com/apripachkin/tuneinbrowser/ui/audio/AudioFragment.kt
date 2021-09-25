package com.apripachkin.tuneinbrowser.ui.audio

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.databinding.AudioFragmentBinding
import com.apripachkin.tuneinbrowser.domain.models.AudioItem
import com.apripachkin.tuneinbrowser.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioFragment : Fragment(R.layout.audio_item_layout) {
    private val binding: AudioFragmentBinding by viewBinding(AudioFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val audioLink = arguments?.getParcelable<AudioItem>(AUDIO_LINK) ?: error("Audio link is required")
    }

    companion object {
        const val AUDIO_LINK = "audio_link"
    }
}