package com.strvacademy.drabekj.moviestrv.model.remote.rest.provider

import com.strvacademy.drabekj.moviestrv.model.entity.*
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RetrofitClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object AuthServiceProvider {
	val REQUEST_TOKEN_CALL_TYPE = "request_token"
	val LOGIN_CALL_TYPE = "login"
	val SESSION_ID_CALL_TYPE = "session_id"


	@Volatile private var sService: AuthService? = null


	interface AuthService {
		@GET("authentication/token/new")
		fun requestToken(): Call<RequestTokenEntity>

		@GET("authentication/token/validate_with_login")
		fun login(
				@Query("username") username: String,
				@Query("password") password: String,
				@Query("request_token") requestToken: String
		): Call<LoginResponseEntity>

		@GET("authentication/session/new")
		fun sessionId(@Query("request_token") requestToken: String): Call<SessionResponseEntity>
	}


	val service: AuthService
		get() {
			if (sService == null) {
				synchronized(AuthServiceProvider::class.java) {
					if (sService == null) {
						sService = RetrofitClient.createService(AuthService::class.java)
					}
				}
			}
			return sService!!
		}
}