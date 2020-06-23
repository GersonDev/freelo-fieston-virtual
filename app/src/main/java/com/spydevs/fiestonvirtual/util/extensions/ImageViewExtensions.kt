package com.spydevs.fiestonvirtual.util.extensions

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.spydevs.fiestonvirtual.R

fun ImageView.loadUrl(url: String, @DrawableRes drawablePlaceholder: Int = R.drawable.default_placeholder) =
    Glide.with(this)
        .load(url)
        .error(drawablePlaceholder)
        .into(this)

fun ImageView.loadUrlWithCorners(url: String, @DrawableRes drawablePlaceholder: Int = R.drawable.default_placeholder) =
    Glide.with(this)
        .load(url)
        .transform(RoundedCorners(20))
        .error(drawablePlaceholder)
        .into(this)
        .clearOnDetach()

fun ImageView.loadUrlForSlider(url: String, @DrawableRes drawablePlaceholder: Int = R.drawable.default_placeholder) {
    val requestImage = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
    Glide.with(this)
        .load(url)
        .override(1100, 260)
        .error(drawablePlaceholder)
        .apply(requestImage)
        .into(this)
        .clearOnDetach()

}

fun clearMemory(context: Context) =
    Glide.get(context)
        .clearMemory()