package com.strvacademy.drabekj.moviestrv.model.remote.rest.provider

import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.model.entity.ResultsEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RetrofitClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object MovieServiceProvider {
	val MOVIE_CALL_TYPE = "movie"
	val POPULAR_MOVIES_CALL_TYPE = "popular_movies"
	val NOW_PLAYINGMOVIES_CALL_TYPE = "now_playing_movies"

	@Volatile private var sService: MovieService? = null


	interface MovieService {
		@GET("movie/{id}")
		fun movie(@Path("id") id: Int): Call<MovieEntity>

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