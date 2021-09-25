package com.apripachkin.tuneinbrowser.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(): ImageLoader {
    override fun loadImageInto(imageView: ImageView, url: String) {
        Glide.with(imageView)
            .load(url)
            .transition(withCrossFade())
            .centerCrop()
            .into(imageView)
    }
}