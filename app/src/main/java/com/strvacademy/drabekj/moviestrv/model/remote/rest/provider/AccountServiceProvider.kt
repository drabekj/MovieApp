package com.strvacademy.drabekj.moviestrv.model.remote.rest.provider

import com.strvacademy.drabekj.moviestrv.model.entity.*
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RetrofitClient
import retrofit2.Call
import retrofit2.http.*


object AccountServiceProvider {
	val ACCOUNT_CALL_TYPE = "account"
	val MARK_FAVOURITE_CALL_TYPE = "mark_favourite"

	@Volatile private var sService: AccountService? = null


	interface AccountService {
		@GET("account")
		fun account( @Query("session_id") sessionId : String): Call<AccountEntity>

		@POST("account/{account_id}/favorite")
		fun markAsFavourite(@Path("account_id") accountId: Int, @Query("session_id") sessionId : String, @Body body: FavouriteEntity): Call<FavouriteResponseEntity>
	}


	val service: AccountService
		get() {
			if (sService == null) {
				synchronized(AccountServiceProvider::class.java) {
					if (sService == null) {
						sService = RetrofitClient.createService(AccountService::class.java)
					}
				}
			}
			return sService!!
		}
}