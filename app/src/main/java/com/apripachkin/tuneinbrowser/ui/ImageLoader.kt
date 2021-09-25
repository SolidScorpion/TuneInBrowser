package com.apripachkin.tuneinbrowser.ui

import android.widget.ImageView

interface ImageLoader {
    fun loadImageInto(imageView: ImageView, url: String)
}