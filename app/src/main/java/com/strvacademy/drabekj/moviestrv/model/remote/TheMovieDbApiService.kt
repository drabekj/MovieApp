package com.strvacademy.drabekj.moviestrv.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDbApiService {

	companion object {
		val BASE_URL = "https://api.themoviedb.org/3/"


		private var service: TheMovieDbApiClient? = null


		fun newInstance(): TheMovieDbApiClient? {
			if (service == null)
				TheMovieDbApiService()

			return service
		}
	}


	init {
		val retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build()

		service = retrofit.create(TheMovieDbApiClient::class.java)
	}
}