package com.strvacademy.drabekj.moviestrv.model.remote.rest.provider

import com.strvacademy.drabekj.moviestrv.model.entity.*
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RetrofitClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object ActorServiceProvider {
	val ACTOR_CALL_TYPE = "actor"
	val ACTOR_MOVIES_CALL_TYPE = "actor_movies"
	val POPULAR_ACTORS_CALL_TYPE = "actors_popular"
	val SEARCH_ACTOR_CALL_TYPE = "search_movie"


	@Volatile private var sService: ActorService? = null


	interface ActorService {
		@GET("person/{id}")
		fun actor(@Path("id") id: Int): Call<ActorEntity>

		@GET("person/{id}/movie_credits")
		fun actorMovies(@Path("id") id: Int): Call<CreditsEntity>

		@GET("person/popular?sort_by=popularity.desc")
		fun popularActors(): Call<ActorsResultsEntity>

		@GET("search/person")
		fun searchActor(@Query("query") query : String): Call<ActorsResultsEntity>
	}


	val service: ActorService
		get() {
			if (sService == null) {
				synchronized(ActorServiceProvider::class.java) {
					if (sService == null) {
						sService = RetrofitClient.createService(ActorService::class.java)
					}
				}
			}
			return sService!!
		}
}