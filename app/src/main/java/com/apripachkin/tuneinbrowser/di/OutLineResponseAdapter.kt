package com.apripachkin.tuneinbrowser.di

import com.apripachkin.tuneinbrowser.data.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import timber.log.Timber

class OutLineResponseAdapter : JsonAdapter<OutLineType>() {
    companion object {
        private const val HEADER_TYPE_MARKER = "children"
        private const val NAME_TYPE_MARKER = "type"
        private const val TEXT_TYPE = "text"
        private const val LINK_TYPE = "link"
        private const val AUDIO_TYPE = "audio"
        private val ITEM_TYPES = JsonReader.Options.of(NAME_TYPE_MARKER, HEADER_TYPE_MARKER)
        private val HEADER_OPTIONS = JsonReader.Options.of(TEXT_TYPE, "key", "children")
    }

    override fun fromJson(reader: JsonReader): OutLineType {
        reader.beginObject()
        // First peek and see if we need to parse child array
        val peek = reader.peekJson()
        var result = Result.failure<OutLineType>(Error())
        Timber.d("Parsing json")
        while (peek.hasNext()) {
            when (peek.selectName(ITEM_TYPES)) {
                0 -> {
                    val outline = parseOutLine(peek, reader)
                    result = Result.success(outline)
                }
                1 -> {
                    Timber.d("Detected header outline")
                    result = Result.success(parseHeaderOutline(reader))
                    break
                }
                else -> peek.skip()
            }
        }
        reader.endObject()
        return result.getOrElse {
            Timber.d("Could not retrieve parse result")
            throw it
        }
    }

    private fun parseOutLine(
        peek: JsonReader,
        reader: JsonReader
    ): OutLineType {
        val outline = when (val typeString = peek.nextString()) {
            LINK_TYPE -> parseLinkOutLine(reader)
            TEXT_TYPE -> parseTextOutline(reader)
            AUDIO_TYPE -> parseAudioOutLine(reader)
            else -> error("Unknown type $typeString")
        }
        return outline
    }

    private fun parseAudioOutLine(reader: JsonReader): AudioOutLine {
        val map = readDataIntoMap(reader)
        return AudioOutLine(
            requireNotNull(map["type"]),
            requireNotNull(map["text"]),
            requireNotNull(map["URL"]),
            map["bitrate"]?.toInt(),
            map["reliability"]?.toInt(),
            requireNotNull(map["subtext"]),
            map["formats"],
            map["playing"],
            map["playing_image"],
            map["item"],
            requireNotNull(map["image"])
        )
    }

    private fun parseTextOutline(reader: JsonReader): TextOutLine {
        val map = readDataIntoMap(reader)
        return TextOutLine(
            requireNotNull(map["text"]),
            requireNotNull(map["element"]),
            requireNotNull(map["type"]),
        )
    }

    private fun readDataIntoMap(reader: JsonReader): Map<String, String> {
        val map = mutableMapOf<String, String>()
        while (reader.hasNext()) {
            map[reader.nextName()] = reader.nextString()
        }
        return map
    }

    private fun parseLinkOutLine(reader: JsonReader): LinkOutLine {
        Timber.d("Parsing link outline")
        val map = readDataIntoMap(reader)
        return LinkOutLine(
            requireNotNull(map["type"]),
            requireNotNull(map["text"]),
            requireNotNull(map["URL"]),
            map["key"]
        )
    }

    private fun parseHeaderOutline(reader: JsonReader): HeaderOutLine {
        var text: String? = null
        var key: String? = null
        val mutableListOf = mutableListOf<OutLineType>()
        while (reader.hasNext()) {
            when (reader.selectName(HEADER_OPTIONS)) {
                0 -> text = reader.nextString()
                1 -> key = reader.nextString()
                2 -> parseHeaderContent(reader, mutableListOf)
                else -> reader.skip()
            }
        }
        return HeaderOutLine(requireNotNull(text), key, mutableListOf)
    }

    private fun parseHeaderContent(
        reader: JsonReader,
        mutableListOf: MutableList<OutLineType>
    ) {
        reader.beginArray()
        while (reader.hasNext()) {
            reader.beginObject()
            val peekJson = reader.peekJson()
            val nameIndex = 0
            while (peekJson.hasNext()) {
                if (peekJson.selectName(JsonReader.Options.of(NAME_TYPE_MARKER)) == nameIndex) {
                    mutableListOf.add(parseOutLine(peekJson, reader))
                } else {
                    peekJson.skip()
                }
            }
            reader.endObject()
        }
        reader.endArray()
    }

    override fun toJson(writer: JsonWriter, value: OutLineType?) {
        throw UnsupportedOperationException("We don't send anything")
    }

    private fun JsonReader.skip() {
        skipName()
        skipValue()
    }
}