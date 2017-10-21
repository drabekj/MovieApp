package com.strvacademy.drabekj.movieapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDbApiProvider {

	companion object {
		val BASE_URL = "https://api.themoviedb.org/3/"


		private var service: TheMovieDbApiService? = null


		fun newInstance(): TheMovieDbApiService? {
			if (service == null)
				TheMovieDbApiProvider()

			return service
		}
	}

	init {
		val retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build()

		service = retrofit.create(TheMovieDbApiService::class.java)
	}
}