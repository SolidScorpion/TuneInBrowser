package com.apripachkin.tuneinbrowser.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apripachkin.tuneinbrowser.databinding.InitialCategoryCardBinding
import com.apripachkin.tuneinbrowser.domain.models.LinkItem

class CategoryAdapter(
    private val items: MutableList<LinkItem> = mutableListOf(),
    private val onClick: (LinkItem) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: InitialCategoryCardBinding,
        onClick: (LinkItem) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var item: LinkItem? = null
        init {
            binding.root.setOnClickListener {
                item?.also(onClick)
            }
        }
        fun bind(outline: LinkItem) {
            item = outline
            binding.categoryCardTitle.text = outline.text
        }
    }

    fun updateItems(newItems: List<LinkItem>) {
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
