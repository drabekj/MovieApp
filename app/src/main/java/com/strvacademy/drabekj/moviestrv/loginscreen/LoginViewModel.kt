package com.strvacademy.drabekj.moviestrv.loginscreen

import android.databinding.ObservableField
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel

class LoginViewModel: BaseViewModel<LoginView>() {
//	val state = ObservableField<Int>()
	val username = ObservableField<String>()
	val password = ObservableField<String>()

//	TODO CRITICAL (pattern Kotlin) how to reuse method form different class)
	fun submitForm() {
		Toast.makeText(MoviesApplication.getContext(), "Logging In...", Toast.LENGTH_SHORT).show()
	}
}