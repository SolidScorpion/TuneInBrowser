package com.apripachkin.tuneinbrowser.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apripachkin.tuneinbrowser.data.Outline
import com.apripachkin.tuneinbrowser.databinding.InitialCategoryCardBinding

class CategoryAdapter(
    private val items: MutableList<Outline> = mutableListOf(),
    private val onClick: (Outline) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: InitialCategoryCardBinding,
                     onClick: (Outline) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var item: Outline? = null
        init {
            binding.root.setOnClickListener {
                item?.also(onClick)
            }
        }
        fun bind(outline: Outline) {
            item = outline
            binding.categoryCardTitle.text = outline.text
        }
    }

    fun updateItems(newItems: List<Outline>) {
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
