package com.apripachkin.tuneinbrowser.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apripachkin.tuneinbrowser.data.LinkOutLine
import com.apripachkin.tuneinbrowser.data.OutLineType
import com.apripachkin.tuneinbrowser.databinding.InitialCategoryCardBinding

class CategoryAdapter(
    private val items: MutableList<LinkOutLine> = mutableListOf(),
    private val onClick: (LinkOutLine) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: InitialCategoryCardBinding,
                     onClick: (LinkOutLine) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var item: LinkOutLine? = null
        init {
            binding.root.setOnClickListener {
                item?.also(onClick)
            }
        }
        fun bind(outline: LinkOutLine) {
            item = outline
            binding.categoryCardTitle.text = outline.text
        }
    }

    fun updateItems(newItems: List<LinkOutLine>) {
        items.clear()
        items.addAll(newItems)
        notifyItemRangeChanged(0, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InitialCategoryCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
