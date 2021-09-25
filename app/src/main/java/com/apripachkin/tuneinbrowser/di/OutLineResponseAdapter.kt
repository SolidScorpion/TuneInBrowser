package com.apripachkin.tuneinbrowser.di

import com.apripachkin.tuneinbrowser.data.*
import com.squareup.moshi.*
import okio.BufferedSource
import timber.log.Timber

class OutLineResponseAdapter : JsonAdapter<OutLineType>() {
    companion object {
        private const val HEADER_TYPE_MARKER = "children"
        private const val NAME_TYPE_MARKER = "type"
        private const val TEXT_TYPE = "text"
        private const val LINK_TYPE = "link"
        private const val AUDIO_TYPE = "audio"
        private val ITEM_TYPES = JsonReader.Options.of(NAME_TYPE_MARKER, HEADER_TYPE_MARKER)
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
                else -> {
                    peek.skipName()
                    peek.skipValue()
                }
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
            LINK_TYPE -> {
                Timber.d("Detected link marker")
                parseLinkOutLine(reader)
            }
            TEXT_TYPE -> {
                Timber.d("Detected text type marker")
                parseTextOutline(reader)
            }
            AUDIO_TYPE -> {
                Timber.d("Detected audio type marker")
                parseAudioOutLine(reader)
            }
            else -> {
                error("Unknown type $typeString")
            }
        }
        return outline
    }

    private fun parseAudioOutLine(reader: JsonReader): AudioOutLine {
        val map = readDataIntoMap(reader)
        return AudioOutLine(
            requireNotNull(map["type"]),
            requireNotNull(map["text"]),
            requireNotNull(map["URL"]),
            requireNotNull(map["bitrate"]).toInt(),
            requireNotNull(map["reliability"]).toInt(),
            requireNotNull(map["subtext"]),
            requireNotNull(map["formats"]),
            requireNotNull(map["playing"]),
            map["playing_image"],
            requireNotNull(map["item"]),
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

    private fun readDataIntoMap(reader: JsonReader) : Map<String, String> {
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
        var key: String?  = null
        val mutableListOf = mutableListOf<OutLineType>()
        while (reader.hasNext()) {
            when (reader.selectName(JsonReader.Options.of("text", "key", "children"))) {
                0 -> {
                    text = reader.nextString()
                }
                1 -> {
                    key = reader.nextString()
                }
                2 -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        reader.beginObject()
                        val peekJson = reader.peekJson()
                        while (peekJson.hasNext()) {
                            if (peekJson.selectName(JsonReader.Options.of(NAME_TYPE_MARKER)) == 0) {
                               val outline = parseOutLine(peekJson, reader)
                                mutableListOf.add(outline)
                            } else {
                                peekJson.skipName()
                                peekJson.skipValue()
                            }
                        }
                        reader.endObject()
                    }
                    reader.endArray()
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        return HeaderOutLine(requireNotNull(text), key, mutableListOf)
    }

    override fun toJson(writer: JsonWriter, value: OutLineType?) {
    }
}