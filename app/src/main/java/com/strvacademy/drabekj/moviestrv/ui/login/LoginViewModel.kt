package com.strvacademy.drabekj.moviestrv.ui.login

import android.databinding.ObservableField
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.entity.AccountEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.AccountServiceProvider
import com.strvacademy.drabekj.moviestrv.utils.KeyStoreUtil
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseViewModel
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.NetworkUtility
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : BaseViewModel<LoginView>() {
	val username = ObservableField<String>()
	val password = ObservableField<String>()

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())
	val mLoginHelper = LoginHelper(this)


	override fun onStart() {
		super.onStart()

		mLoginHelper.getRequestToken()
	}

	//	TODO CRITICAL (pattern Kotlin) how to reuse method form different class)
	fun submitForm() {
		// TODO FATAL validate form
		mLoginHelper.login(username.get(), password.get())
	}

	fun loginSuccess() {
		// save encrypted sessionID
		if(MoviesApplication.sessionID != null)
			KeyStoreUtil.storeSecret(MoviesApplication.sessionID!!)

		// load accountID
		loadAccountId()

		view?.onLoginSuccessful()
	}

	fun loginFail() {
		view?.dismissLoadingDialog()
	}

	private fun loadAccountId() {
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