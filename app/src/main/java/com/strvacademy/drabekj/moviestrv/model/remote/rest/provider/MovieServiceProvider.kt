package com.strvacademy.drabekj.moviestrv.model.remote.rest.provider

import com.strvacademy.drabekj.moviestrv.model.entity.CreditsEntity
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.model.entity.ResultsEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RetrofitClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object MovieServiceProvider {
	val MOVIE_CALL_TYPE = "movie"
	val MOVIE_CREDITS_CALL_TYPE = "movie_credits"
	val POPULAR_MOVIES_CALL_TYPE = "movies_popular"
	val NOW_PLAYING_MOVIES_CALL_TYPE = "movies_now_playing"

	@Volatile private var sService: MovieService? = null


	interface MovieService {
		@GET("movie/{id}")
		fun movie(@Path("id") id: Int): Call<MovieEntity>

		@GET("movie/{id}/credits")
		fun movieCredits(@Path("id") id: Int): Call<CreditsEntity>

		@GET("discover/movie?sort_by=popularity.desc")
		fun popularMovies(): Call<ResultsEntity>

		@GET("movie/now_playing")
		fun nowPlayingMovies(): Call<ResultsEntity>
	}


	val service: MovieService
		get() {
			if (sService == null) {
				synchronized(MovieServiceProvider::class.java) {
					if (sService == null) {
						sService = RetrofitClient.createService(MovieService::class.java)
					}
				}
			}
			return sService!!
		}
}