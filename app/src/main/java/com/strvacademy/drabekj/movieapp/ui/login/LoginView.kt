package com.strvacademy.drabekj.movieapp.ui.login

import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface LoginView: BaseView {
	fun onLogInClick()
	fun showLoadingDialog()
	fun dismissLoadingDialog()
	fun onLoginSuccessful()
}