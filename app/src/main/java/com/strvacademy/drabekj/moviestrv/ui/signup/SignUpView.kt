package com.strvacademy.drabekj.moviestrv.ui.signup

import com.strvacademy.drabekj.moviestrv.utils.BaseView
import com.strvacademy.drabekj.moviestrv.ui.signup.SignUpViewModel.Companion.FieldCheck


interface SignUpView: BaseView {
	fun onSignUpClick()
	fun showError(field: FieldCheck, enabled: Boolean)
}