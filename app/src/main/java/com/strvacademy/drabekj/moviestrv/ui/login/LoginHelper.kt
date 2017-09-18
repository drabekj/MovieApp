package com.strvacademy.drabekj.moviestrv.ui.login

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.entity.LoginResponseEntity
import com.strvacademy.drabekj.moviestrv.model.entity.RequestTokenEntity
import com.strvacademy.drabekj.moviestrv.model.entity.SessionResponseEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.AuthServiceProvider
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.rest.call.Callback
import org.alfonz.utility.Logcat
import org.alfonz.utility.NetworkUtility
import retrofit2.Call
import retrofit2.Response

class LoginHelper(val viewModel: LoginViewModel) {
	val requestToken = ObservableField<String>()
	val loggedIn = ObservableField<Boolean>()

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())

	//	Get Request Token
	fun getRequestToken() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = AuthServiceProvider.REQUEST_TOKEN_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = AuthServiceProvider.service.requestToken()
				val callback = RequestTokenCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// offline
			// TODO handle case where request token not received
		}
	}

	inner class RequestTokenCallback(callManager: CallManager) : Callback<RequestTokenEntity>(callManager) {
		override fun onSuccess(call: Call<RequestTokenEntity>, response: Response<RequestTokenEntity>) {
			requestToken.set(response.body()?.requestToken)
			Logcat.d("request token received: " + requestToken.get())
		}

		override fun onError(call: Call<RequestTokenEntity>, exception: HttpException) {
			viewModel.handleError(mCallManager.getHttpErrorMessage(exception))
			Logcat.e("Error while getting request token occurred: " + exception.toString())
			// TODO handle case where request token not received
		}

		override fun onFail(call: Call<RequestTokenEntity>, throwable: Throwable) {
			viewModel.handleError(mCallManager.getHttpErrorMessage(throwable))
			Logcat.e("Error while getting request token occurred: " + throwable.message)
			// TODO handle case where request token not received
		}
	}

	//	Login (Validate Request Token)
	fun login(username: String, password: String) {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			viewModel.view?.showLoadingDialog()

			val callType = AuthServiceProvider.LOGIN_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = AuthServiceProvider.service.login(username, password, requestToken.get())
				val callback = LoginCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// offline
			// TODO handle case where request token not received
			viewModel.loginFail()
		}
	}

	inner class LoginCallback(callManager: CallManager) : Callback<LoginResponseEntity>(callManager) {
		override fun onSuccess(call: Call<LoginResponseEntity>, response: Response<LoginResponseEntity>) {
			loggedIn.set(response.body()?.success)
			Logcat.d("logged in: " + loggedIn.get())

			// get sessionID
			getSessionID()
		}

		override fun onError(call: Call<LoginResponseEntity>, exception: HttpException) {
			viewModel.handleError(mCallManager.getHttpErrorMessage(exception))
			Logcat.e("Error while logging in occurred: " + exception.toString())
			// TODO handle case where request token not received
			viewModel.loginFail()
		}

		override fun onFail(call: Call<LoginResponseEntity>, throwable: Throwable) {
			viewModel.handleError(mCallManager.getHttpErrorMessage(throwable))
			Logcat.e("Error while logging in occurred: " + throwable.message)
			// TODO handle case where request token not received
			viewModel.loginFail()
		}
	}

	// Get SessionID
	private fun getSessionID() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = AuthServiceProvider.SESSION_ID_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = AuthServiceProvider.service.sessionId(requestToken.get())
				val callback = SessionIDCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// offline
			// TODO handle case where request token not received
			viewModel.loginFail()
		}
	}

	inner class SessionIDCallback(callManager: CallManager) : Callback<SessionResponseEntity>(callManager) {
		override fun onSuccess(call: Call<SessionResponseEntity>, response: Response<SessionResponseEntity>) {
			Logcat.d("get sessionID success " + response.body()?.success)

			MoviesApplication.sessionID = response.body()?.sessionId
			viewModel.loginSuccess()
		}

		override fun onError(call: Call<SessionResponseEntity>, exception: HttpException) {
			viewModel.handleError(mCallManager.getHttpErrorMessage(exception))
			Logcat.e("Error while getting SessionID in occurred: " + exception.toString())
			// TODO handle case where request token not received
			viewModel.loginFail()
		}

		override fun onFail(call: Call<SessionResponseEntity>, throwable: Throwable) {
			viewModel.handleError(mCallManager.getHttpErrorMessage(throwable))
			Logcat.e("Error while getting SessionID in occurred: " + throwable.message)
			// TODO handle case where request token not received
			viewModel.loginFail()
		}
	}
}