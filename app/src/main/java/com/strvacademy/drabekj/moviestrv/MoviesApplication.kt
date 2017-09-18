package com.strvacademy.drabekj.moviestrv

import android.app.Application
import android.content.Context
import com.strvacademy.drabekj.moviestrv.utils.KeyStoreUtil

import org.alfonz.utility.Logcat


class MoviesApplication : Application() {
	init {
		sInstance = this
	}


	override fun onCreate() {
		super.onCreate()

		// init logcat
		Logcat.init(MoviesConfig.LOGS, "MoviesApp")

		// get sessionID
		KeyStoreUtil.loadSecret()
	}

	companion object {
		private var sInstance: MoviesApplication? = null
		var sessionID: String? = null


		val context: Context
			get() = sInstance!!

		fun isUserLoggedIn(): Boolean {
			return sessionID != null
		}
	}
}