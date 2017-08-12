package com.strvacademy.drabekj.moviestrv.signupscreen

import com.strvacademy.drabekj.moviestrv.utils.BaseView
import com.strvacademy.drabekj.moviestrv.signupscreen.SignUpViewModel.Companion.FieldCheck


interface SignUpView: BaseView {
	fun onSignUpClick()
	fun showError(field: FieldCheck, enabled: Boolean)
}