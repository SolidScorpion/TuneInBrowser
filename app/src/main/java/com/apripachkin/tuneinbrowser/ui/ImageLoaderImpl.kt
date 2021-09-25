package com.apripachkin.tuneinbrowser.ui

import android.widget.ImageView
import com.apripachkin.tuneinbrowser.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import timber.log.Timber
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(): ImageLoader {
    override fun loadImageInto(imageView: ImageView, url: String) {
        Timber.d("loading $url")
        Glide.with(imageView)
            .load(url)
            .transition(withCrossFade())
            .centerCrop()
            .into(imageView)
    }

    override fun clearImageView(imageView: ImageView) {
        Glide.with(imageView).clear(imageView)
    }
}