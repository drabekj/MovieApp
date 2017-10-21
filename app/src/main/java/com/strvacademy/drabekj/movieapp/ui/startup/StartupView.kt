package com.strvacademy.drabekj.movieapp.ui.startup

import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface StartupView: BaseView {
	fun onSignUpClick()
	fun onLogInClick()
	fun onSkipClick()
}