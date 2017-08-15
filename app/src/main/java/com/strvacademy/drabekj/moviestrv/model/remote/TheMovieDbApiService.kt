package com.strvacademy.drabekj.moviestrv.model.remote

import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.PopularMoviesDataResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class TheMovieDbApiService {

	val BASE_URL = "https://api.themoviedb.org/3"

	private val movieDbService: TheMovieDbApiClient


	init {
		val retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build()

		movieDbService = retrofit.create(TheMovieDbApiClient::class.java)
	}

	fun getPopMovies(): Call<PopularMoviesDataResponse>{
		return movieDbService.getPopularMovies()
	}

}