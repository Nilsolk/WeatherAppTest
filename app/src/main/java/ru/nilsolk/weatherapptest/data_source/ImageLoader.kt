package ru.nilsolk.weatherapptest.data_source

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImageLoader(private val context: Context) {
    fun loadImage(url: String, imageView: ImageView) {
        Glide.with(context)
            .load("https:$url")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageView)
    }
}