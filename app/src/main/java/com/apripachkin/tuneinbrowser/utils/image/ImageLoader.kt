package com.apripachkin.tuneinbrowser.utils.image

import android.widget.ImageView

interface ImageLoader {
    fun loadImageInto(imageView: ImageView, url: String)
    fun clearImageView(imageView: ImageView)
}