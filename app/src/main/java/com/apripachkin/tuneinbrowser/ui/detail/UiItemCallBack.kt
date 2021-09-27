package com.apripachkin.tuneinbrowser.ui.detail

import androidx.recyclerview.widget.DiffUtil
import com.apripachkin.tuneinbrowser.domain.models.AudioItem
import com.apripachkin.tuneinbrowser.domain.models.HeaderItem
import com.apripachkin.tuneinbrowser.domain.models.LinkItem
import com.apripachkin.tuneinbrowser.domain.models.TextItem
import com.apripachkin.tuneinbrowser.domain.models.UiItem

class UiItemCallBack : DiffUtil.ItemCallback<UiItem>() {
    override fun areItemsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
        return when (oldItem) {
            is AudioItem -> oldItem.url == (newItem as? AudioItem)?.url ?: false
            is HeaderItem -> oldItem.text == (newItem as? HeaderItem)?.text ?: false
            is LinkItem -> oldItem.url == (newItem as? LinkItem)?.url ?: false
            is TextItem -> oldItem.text == (newItem as? TextItem)?.text ?: false
        }
    }

    override fun areContentsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
        return oldItem == newItem
    }
}
