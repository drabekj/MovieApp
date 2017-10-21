package com.strvacademy.drabekj.movieapp.model.remote.rest.provider

import com.strvacademy.drabekj.movieapp.model.entity.*
import com.strvacademy.drabekj.movieapp.model.remote.rest.RetrofitClient
import retrofit2.Call
import retrofit2.http.*


object MovieServiceProvider {
	val MOVIE_CALL_TYPE = "movie"
	val POPULAR_MOVIES_CALL_TYPE = "movies_popular"
	val NOW_PLAYING_MOVIES_CALL_TYPE = "movies_now_playing"
	val SEARCH_MOVIE_CALL_TYPE = "search_movie"

	@Volatile private var sService: MovieService? = null


	interface MovieService {
		@GET("movie/{id}")
		fun movieDetail(@Path("id") id: Int, @Query("append_to_response") append_to_response : String = "credits,videos,images"): Call<MovieEntity>

		@GET("discover/movie?sort_by=popularity.desc")
		fun popularMovies(): Call<MovieResultsEntity>

		@GET("movie/now_playing")
		fun nowPlayingMovies(): Call<MovieResultsEntity>

		@GET("search/movie")
		fun searchMovie(@Query("query") query : String): Call<MovieResultsEntity>
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