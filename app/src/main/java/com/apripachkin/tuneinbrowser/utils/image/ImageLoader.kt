package com.apripachkin.tuneinbrowser.utils.image

import android.widget.ImageView

/**
 * Image loader interface to hide implementation details and to allow swift switching of image
 * loading libraries
 */
interface ImageLoader {
    fun loadImageInto(imageView: ImageView, url: String)
    fun clearImageView(imageView: ImageView)
}