package com.strvacademy.drabekj.moviestrv.model.remote.rest.http

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class QueryRequestInterceptor : Interceptor {
	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val original = chain.request()
		val originalHttpUrl = original.url()

		val url = originalHttpUrl.newBuilder()
				.addQueryParameter("api_key", "bd7f681651d1b15fc0fe5a1892ae0151")
				.build()

		// Request customization: add request headers
		val requestBuilder = original.newBuilder()
				.url(url)

		val request = requestBuilder.build()
		return chain.proceed(request)
	}
}