package com.apripachkin.tuneinbrowser.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.databinding.AudioItemLayoutBinding
import com.apripachkin.tuneinbrowser.databinding.HeaderItemLayoutBinding
import com.apripachkin.tuneinbrowser.databinding.LinkItemLayoutBinding
import com.apripachkin.tuneinbrowser.databinding.TextItemLayoutBinding
import com.apripachkin.tuneinbrowser.domain.models.AudioItem
import com.apripachkin.tuneinbrowser.domain.models.HeaderItem
import com.apripachkin.tuneinbrowser.domain.models.LinkItem
import com.apripachkin.tuneinbrowser.domain.models.TextItem
import com.apripachkin.tuneinbrowser.domain.models.UiItem
import com.apripachkin.tuneinbrowser.ui.ImageLoader

class OutLineItemAdapter(
    private val items: MutableList<UiItem> = mutableListOf(),
    private val imageLoader: ImageLoader,
    private val itemClick: (UiItem) -> Unit
) : RecyclerView.Adapter<OutLineItemAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ViewBinding,
        private val imageLoader: ImageLoader,
        itemClick: (UiItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private var item: UiItem? = null

        init {
            binding.root.setOnClickListener {
                item?.also(itemClick)
            }
        }

        fun bind(bindItem: UiItem) {
            item = bindItem
            when (bindItem) {
                is AudioItem -> {
                    bindAudioItem(bindItem)
                }
                is HeaderItem -> {
                    val headerItemLayoutBinding = binding as HeaderItemLayoutBinding
                    headerItemLayoutBinding.headerItemTv.text = bindItem.text
                    val drawable =
                        bindItem.moreItemsLink?.let { R.drawable.ic_baseline_arrow_forward_24 } ?: 0
                    headerItemLayoutBinding.headerItemTv.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        drawable,
                        0
                    )
                }
                is LinkItem -> {
                    val linkItemLayoutBinding = binding as LinkItemLayoutBinding
                    linkItemLayoutBinding.linkItemTv.text = bindItem.text
                }
                is TextItem -> {
                    val textItemLayoutBinding = binding as TextItemLayoutBinding
                    textItemLayoutBinding.textItemTv.text = bindItem.text
                }
            }
        }

        private fun bindAudioItem(bindItem: AudioItem) {
            val audioItemLayoutBinding = binding as AudioItemLayoutBinding
            val playingImage = bindItem.image
            imageLoader.loadImageInto(audioItemLayoutBinding.cardImage, playingImage)
            audioItemLayoutBinding.audioItemHeaderTv.text = bindItem.text
            audioItemLayoutBinding.audioItemSubHeaderTv.text = bindItem.playing
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].itemId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            R.layout.audio_item_layout -> AudioItemLayoutBinding.inflate(inflater, parent, false)
            R.layout.link_item_layout -> LinkItemLayoutBinding.inflate(inflater, parent, false)
            R.layout.text_item_layout -> TextItemLayoutBinding.inflate(inflater, parent, false)
            R.layout.header_item_layout -> HeaderItemLayoutBinding.inflate(inflater, parent, false)
            else -> error("Unknown binding")
        }
        return ViewHolder(binding, imageLoader, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
    fun updateData(body: List<UiItem>) {
        items.clear()
        items.addAll(body)
        notifyItemRangeChanged(0, body.size)
    }
}
