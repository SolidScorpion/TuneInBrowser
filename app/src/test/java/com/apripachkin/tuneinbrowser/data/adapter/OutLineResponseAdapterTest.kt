package com.apripachkin.tuneinbrowser.data.adapter

import com.apripachkin.tuneinbrowser.TestObjects
import org.junit.Test

class OutLineResponseAdapterTest {
    private val outLineResponseAdapter = OutLineResponseAdapter()

    @Test
    fun testLinkOutlineParsed() {
        val fromJson = outLineResponseAdapter.fromJson(provideJsonString("link_outline.json"))
        assert(TestObjects.sampleLinkOutline == fromJson)
    }

    @Test
    fun testAudioOutLineParsed() {
        val fromJson = outLineResponseAdapter.fromJson(provideJsonString("audio_outline.json"))
        assert(TestObjects.sampleAudioOutLine == fromJson)
    }

    @Test
    fun testHeaderOutLineParsed() {
        val fromJson = outLineResponseAdapter.fromJson(provideJsonString("header_outline.json"))
        assert(TestObjects.sampleHeaderOutLine == fromJson)
    }

    private fun provideJsonString(resourceName: String): String {
        return javaClass.classLoader.getResourceAsStream(resourceName).bufferedReader().readText()
    }
}
