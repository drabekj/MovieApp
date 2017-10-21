package com.strvacademy.drabekj.movieapp.ui.main

import com.strvacademy.drabekj.movieapp.MoviesApplication
import com.strvacademy.drabekj.movieapp.model.entity.AccountEntity
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.movieapp.model.remote.rest.provider.AccountServiceProvider
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseViewModel
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.NetworkUtility
import retrofit2.Call
import retrofit2.Response

class MainViewModel : BaseViewModel<MainView>() {
	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())

	// TODO  critical! if user is offline during onCreate in MainActivity, accountID will be null => crash on Profile/Favourite
	fun loadAccountId() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = AccountServiceProvider.FAVOURITES_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = AccountServiceProvider.service.account(MoviesApplication.sessionID!!)
				val callback = AccountIDCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		}
	}

	inner class AccountIDCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<AccountEntity>(callManager) {
		override fun onSuccess(call: Call<AccountEntity>, response: Response<AccountEntity>) {
			MoviesApplication.accountID.set(response.body()?.id)
		}

		override fun onError(call: Call<AccountEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<AccountEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}
}