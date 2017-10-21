package com.strvacademy.drabekj.movieapp.ui.startup

import android.content.Intent
import android.view.LayoutInflater
import com.strvacademy.drabekj.movieapp.databinding.FragmentStartupBinding
import com.strvacademy.drabekj.movieapp.ui.login.LoginActivity
import com.strvacademy.drabekj.movieapp.ui.main.MainActivity
import com.strvacademy.drabekj.movieapp.ui.signup.SignUpActivity
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseFragment

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
//		val uri = Uri.parse("http://www.google.com") // missing 'http://' will cause crashed
//		val intent = Intent(Intent.ACTION_VIEW, uri)
//		startActivity(intent)
	}

	override fun onLogInClick() {
		startActivity(Intent(context, LoginActivity::class.java))
	}

	override fun onSkipClick() {
		MainActivity.startAsIntent(context, skipLogin = true)
	}
}