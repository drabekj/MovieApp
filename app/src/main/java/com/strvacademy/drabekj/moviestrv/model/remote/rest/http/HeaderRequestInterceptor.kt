package com.strvacademy.drabekj.moviestrv.model.remote.rest.http

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderRequestInterceptor : Interceptor {
	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val builder = chain.request().newBuilder()
		builder.addHeader("Accept", "application/json")
		builder.addHeader("Accept-Charset", "utf-8")
		builder.addHeader("Content-Type", "application/json")

		val request = builder.build()
		return chain.proceed(request)
	}
}
