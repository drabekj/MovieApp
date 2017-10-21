package com.strvacademy.drabekj.movieapp.model.remote.rest

import org.alfonz.rest.HttpLogger
import org.alfonz.utility.Logcat

class RestHttpLogger : HttpLogger {
	override fun logSuccess(message: String) {
		Logcat.d(message)
	}


	override fun logError(message: String) {
		Logcat.d(message)
	}


	override fun logFail(message: String) {
		Logcat.d(message)
	}
}