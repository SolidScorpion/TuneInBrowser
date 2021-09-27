package com.apripachkin.tuneinbrowser.domain.mapper

import com.apripachkin.tuneinbrowser.TestObjects
import com.apripachkin.tuneinbrowser.data.models.LinkOutLine
import com.apripachkin.tuneinbrowser.domain.models.AudioItem
import com.apripachkin.tuneinbrowser.domain.models.HeaderItem
import com.apripachkin.tuneinbrowser.domain.models.LinkItem
import com.apripachkin.tuneinbrowser.domain.models.TextItem
import org.junit.Test

class UiItemMapperTest {
    private val mapper = UiItemMapper()
    @Test
    fun testConvertLinkOutLine() {
        val incomingData = List(3) { TestObjects.sampleLinkOutline }
        val toUiItems = mapper.toUiItems(incomingData)
        assert(toUiItems.all { it is LinkItem && it.url == TestObjects.sampleLinkOutline.URL })
    }

    @Test
    fun testConvertAudioOutLine() {
        val toUiItems = mapper.toUiItems(listOf(TestObjects.sampleAudioOutLine))
        assert(toUiItems.size == 1)
        val uiItem = toUiItems[0]
        assert(uiItem is AudioItem)
        assert((uiItem as AudioItem).url == TestObjects.sampleAudioOutLine.URL)
    }

    @Test
    fun testConvertTextOutLine() {
        val toUiItems = mapper.toUiItems(listOf(TestObjects.sampleTextOutLine))
        assert(toUiItems.all { it is TextItem && it.text == TestObjects.sampleTextOutLine.text })
    }

    @Test
    fun convertHeaderOutline() {
        val toUiItems = mapper.toUiItems(listOf(TestObjects.sampleHeaderOutLine))
        assert(toUiItems.size == 2)
        val uiItem = toUiItems[0]
        val linkOutLine = TestObjects.sampleHeaderOutLine.children[1] as LinkOutLine
        assert(uiItem is HeaderItem && uiItem.moreItemsLink == linkOutLine.URL)
        val value = toUiItems[1]
        assert(value is AudioItem && value.url == TestObjects.sampleAudioOutLine.URL)
    }
}
