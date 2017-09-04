package com.strvacademy.drabekj.moviestrv.utils

import android.graphics.drawable.Drawable
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.strvacademy.drabekj.moviestrv.MoviesConfig
import com.strvacademy.drabekj.moviestrv.R


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
	GlideApp
			.with(imageView.context)
			.load(url)
			.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
			.placeholder(R.drawable.ic_image_black_placeholder_24dp)
			.transition(DrawableTransitionOptions.withCrossFade())
			.into(imageView)
}


@BindingAdapter(value = *arrayOf("imageUrl", "imagePlaceholder", "imageError"), requireAll = false)
fun setImageUrl(imageView: ImageView, url: String, placeholder: Drawable?, error: Drawable?) {
	val requestManager = GlideApp.with(imageView.context)
	val builder = requestManager.load(url)
	GlideUtility.setupRequestBuilder(builder, placeholder, error)

	builder.into(imageView)
}