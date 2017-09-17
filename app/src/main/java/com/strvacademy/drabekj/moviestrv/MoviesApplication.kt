package com.strvacademy.drabekj.moviestrv

import android.app.Application
import android.content.Context

import org.alfonz.utility.Logcat


class MoviesApplication : Application() {
	init {
		sInstance = this
	}


	override fun onCreate() {
		super.onCreate()

		// init logcat
		Logcat.init(MoviesConfig.LOGS, "MoviesApp")
	}

	companion object {
		private var sInstance: MoviesApplication? = null


		val context: Context
			get() = sInstance!!
	}
}