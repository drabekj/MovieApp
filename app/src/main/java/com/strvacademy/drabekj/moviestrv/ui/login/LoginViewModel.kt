package com.strvacademy.drabekj.moviestrv.ui.login

import android.databinding.ObservableField
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseViewModel
import org.alfonz.rest.call.CallManager

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
		Toast.makeText(MoviesApplication.context, "Logging In...", Toast.LENGTH_SHORT).show()

		// TODO FATAL validate form
		mLoginHelper.login(username.get(), password.get())
	}
}