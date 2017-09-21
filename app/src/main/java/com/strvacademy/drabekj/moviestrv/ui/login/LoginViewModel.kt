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

		view?.onLoginSuccessful()
	}

	fun loginFail() {
		view?.dismissLoadingDialog()
	}
}