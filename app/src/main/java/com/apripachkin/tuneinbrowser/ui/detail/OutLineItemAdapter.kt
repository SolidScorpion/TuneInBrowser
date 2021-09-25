package com.apripachkin.tuneinbrowser.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.data.*
import com.apripachkin.tuneinbrowser.databinding.AudioItemLayoutBinding
import com.apripachkin.tuneinbrowser.databinding.HeaderItemLayoutBinding
import com.apripachkin.tuneinbrowser.databinding.LinkItemLayoutBinding
import com.apripachkin.tuneinbrowser.databinding.TextItemLayoutBinding
import com.apripachkin.tuneinbrowser.ui.ImageLoader

class OutLineItemAdapter(
    private val items: MutableList<OutLineType> = mutableListOf(),
    private val imageLoader: ImageLoader,
    private val itemClick: (OutLineType) -> Unit
) : RecyclerView.Adapter<OutLineItemAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ViewBinding,
        private val imageLoader: ImageLoader,
        itemClick: (OutLineType) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private var item: OutLineType? = null

        init {
            binding.root.setOnClickListener {
                item?.also(itemClick)
            }
        }

        fun bind(bindItem: OutLineType) {
            item = bindItem
            when (bindItem) {
                is AudioOutLine -> {
                    bindAudioItem(bindItem)
                }
                is HeaderOutLine -> {
                    val headerItemLayoutBinding = binding as HeaderItemLayoutBinding
                    headerItemLayoutBinding.headerItemTv.text = bindItem.text
                }
                is LinkOutLine -> {
                    val linkItemLayoutBinding = binding as LinkItemLayoutBinding
                    linkItemLayoutBinding.linkItemTv.text = bindItem.text
                }
                is TextOutLine -> {
                    val textItemLayoutBinding = binding as TextItemLayoutBinding
                    textItemLayoutBinding.textItemTv.text = bindItem.text
                }
            }
        }

        private fun bindAudioItem(bindItem: AudioOutLine) {
            val audioItemLayoutBinding = binding as AudioItemLayoutBinding
            bindItem.playing_image?.also {
                imageLoader.loadImageInto(audioItemLayoutBinding.cardImage, it)
            }
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
    fun updateData(body: List<OutLineType>) {
        items.clear()
        items.addAll(body)
        notifyItemRangeChanged(0, body.size)
    }
}
