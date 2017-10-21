package com.strvacademy.drabekj.movieapp.ui.login

import android.databinding.ObservableField
import com.strvacademy.drabekj.movieapp.MoviesApplication
import com.strvacademy.drabekj.movieapp.utils.KeyStoreUtil
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseViewModel

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