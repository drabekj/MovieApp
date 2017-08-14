package com.strvacademy.drabekj.moviestrv.ui.startup

import android.content.Intent
import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentStartupBinding
import com.strvacademy.drabekj.moviestrv.ui.login.LoginActivity
import com.strvacademy.drabekj.moviestrv.ui.signup.SignUpActivity
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment

// TODO !!! what if I don't need ViewModel?!
class StartupFragment: BaseFragment<StartupView, StartupViewModel, FragmentStartupBinding>(), StartupView {

	override fun getViewModelClass(): Class<StartupViewModel> {
		return StartupViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentStartupBinding {
		return FragmentStartupBinding.inflate(inflater!!)
	}

	override fun onSignUpClick() {
		startActivity(Intent(context, SignUpActivity::class.java))
	}

	override fun onLogInClick() {
		startActivity(Intent(context, LoginActivity::class.java))
	}
}