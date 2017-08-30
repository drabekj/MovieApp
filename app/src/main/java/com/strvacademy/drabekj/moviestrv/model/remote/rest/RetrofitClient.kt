package com.strvacademy.drabekj.moviestrv.model.remote.rest

import com.google.gson.GsonBuilder
import com.strvacademy.drabekj.moviestrv.MoviesConfig
import com.strvacademy.drabekj.moviestrv.model.remote.rest.http.HeaderRequestInterceptor
import com.strvacademy.drabekj.moviestrv.model.remote.rest.http.QueryRequestInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.alfonz.utility.Logcat
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
	@Volatile private var sRetrofit: Retrofit? = null


	val retrofit: Retrofit
		get() {
			if (sRetrofit == null) {
				synchronized(RetrofitClient::class.java) {
					if (sRetrofit == null) {
						sRetrofit = buildRetrofit()
					}
				}
			}
			return sRetrofit!!
		}


	fun <T> createService(service: Class<T>): T {
		return retrofit.create(service)
	}


	private fun buildRetrofit(): Retrofit {
		val builder = Retrofit.Builder()
		builder.baseUrl(MoviesConfig.REST_BASE_URL)
		builder.client(buildClient())
		builder.addConverterFactory(createConverterFactory())
		return builder.build()
	}


	private fun buildClient(): OkHttpClient {
		val builder = OkHttpClient.Builder()
		builder.connectTimeout(30, TimeUnit.SECONDS)
		builder.readTimeout(30, TimeUnit.SECONDS)
		builder.writeTimeout(30, TimeUnit.SECONDS)
		builder.addInterceptor(HeaderRequestInterceptor())
		builder.addInterceptor(QueryRequestInterceptor())
		builder.addNetworkInterceptor(createLoggingInterceptor())
		return builder.build()
	}


	private fun createLoggingInterceptor(): Interceptor {
		val logger = HttpLoggingInterceptor.Logger { message -> Logcat.d(message) }
		val interceptor = HttpLoggingInterceptor(logger)
		interceptor.level = if (MoviesConfig.LOGS) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
		return interceptor
	}


	private fun createConverterFactory(): Converter.Factory {
		val builder = GsonBuilder()
		builder.setDateFormat("EEE MMM d HH:mm:ss 'UTC'zzzzz yyyy")
		val gson = builder.create()
		return GsonConverterFactory.create(gson)
	}
}