package com.strvacademy.drabekj.moviestrv.ui.login

import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseView


interface LoginView: BaseView {
	fun onLogInClick()
	fun showLoadingDialog()
	fun dismissLoadingDialog()
	fun onLoginSuccessful()
}