package com.strvacademy.drabekj.moviestrv.startupscreen

import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentStartupBinding
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
		showToast("SignUp click")
	}

	override fun onLogInClick() {
		showToast("LogIn click")
	}
}