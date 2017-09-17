package com.strvacademy.drabekj.moviestrv.utils.glide

import org.alfonz.utility.Logcat
import com.bumptech.glide.request.RequestListener
import android.graphics.Bitmap
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.load.engine.DiskCacheStrategy
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target
import com.strvacademy.drabekj.moviestrv.MoviesConfig
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.glide.GlideRequest
import org.alfonz.graphics.drawable.CircularDrawable


object GlideUtility {

	fun setupRequestBuilder(builder: GlideRequest<Drawable>, placeholder: Drawable?, error: Drawable?) {
		builder.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

		if (builder is RequestBuilder<Drawable>) {
			(builder as RequestBuilder<Drawable>).transition(DrawableTransitionOptions.withCrossFade())
		}

		if (MoviesConfig.LOGS) {
			builder.listener(createLogRequestListener())
		}

		if (placeholder != null) {
			builder.placeholder(placeholder)
		}

		if (error != null) {
			builder.error(error)
		} else {
			builder.error(R.drawable.ic_image_black_placeholder_24dp)
		}
	}


	fun createCircularTarget(imageView: ImageView): BitmapImageViewTarget {
		return object : BitmapImageViewTarget(imageView) {
			override fun setResource(resource: Bitmap?) {
				val drawable = CircularDrawable(resource)
				imageView.setImageDrawable(drawable)
			}
		}
	}


	fun createLogRequestListener(): RequestListener<Drawable> {
		return object : RequestListener<Drawable> {
			override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
				Logcat.d("%s / %s / isFirstResource=%s", e, model, isFirstResource)
				e?.printStackTrace()
				return false
			}

			override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
				Logcat.d("%s / dataSource=%s / isFirstResource=%s", model, dataSource, isFirstResource)
				return false
			}
		}
	}

}