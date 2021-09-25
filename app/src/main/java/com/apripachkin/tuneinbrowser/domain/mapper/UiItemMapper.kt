package com.apripachkin.tuneinbrowser.domain.mapper

import com.apripachkin.tuneinbrowser.data.AudioOutLine
import com.apripachkin.tuneinbrowser.data.HeaderOutLine
import com.apripachkin.tuneinbrowser.data.LinkOutLine
import com.apripachkin.tuneinbrowser.data.OutLineType
import com.apripachkin.tuneinbrowser.data.TextOutLine
import com.apripachkin.tuneinbrowser.domain.models.AudioItem
import com.apripachkin.tuneinbrowser.domain.models.HeaderItem
import com.apripachkin.tuneinbrowser.domain.models.LinkItem
import com.apripachkin.tuneinbrowser.domain.models.TextItem
import com.apripachkin.tuneinbrowser.domain.models.UiItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiItemMapper @Inject constructor() {

    fun toUiItems(data: List<OutLineType>): List<UiItem> {
        val result = mutableListOf<UiItem>()
        data.forEach {
            convertOutLineToUiItem(it, result)
        }
        return result
    }

    private fun convertOutLineToUiItem(outline: OutLineType, resultList: MutableList<UiItem>) {
        when (outline) {
            is AudioOutLine -> {
                resultList.add(convertToAudio(outline))
            }
            is HeaderOutLine -> {
                val partition = outline.children.partition {
                    it is AudioOutLine || it is LinkOutLine && it.key == null
                }
                val mappedItems = partition.first.map { item ->
                    when (item) {
                        is AudioOutLine -> convertToAudio(item)
                        is LinkOutLine -> convertToLink(item)
                        is TextOutLine -> convertToText(item)
                        else -> error("Header type can not contain nested headers")
                    }
                }
                val headerOutLine = partition.second.find {
                    it is LinkOutLine && it.key?.startsWith("next") ?: false
                }

                resultList.add(HeaderItem(outline.text, (headerOutLine as? LinkOutLine)?.URL))
                resultList.addAll(mappedItems)
            }
            is LinkOutLine -> {
                resultList.add(convertToLink(outline))
            }
            is TextOutLine -> {
                resultList.add(convertToText(outline))
            }
        }
    }

    private fun convertToAudio(outline: AudioOutLine) = AudioItem(
        outline.text,
        outline.URL,
        outline.subtext,
        outline.playing,
        outline.playing_image,
        outline.image
    )

    private fun convertToLink(outline: LinkOutLine) = LinkItem(outline.text, outline.URL)

    private fun convertToText(outline: TextOutLine) = TextItem(outline.text)
}