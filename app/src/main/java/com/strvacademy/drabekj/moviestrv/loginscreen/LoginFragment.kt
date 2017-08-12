package com.strvacademy.drabekj.moviestrv.loginscreen

import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentLoginBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment

class LoginFragment: BaseFragment<LoginView, LoginViewModel, FragmentLoginBinding>(), LoginView {

	override fun getViewModelClass(): Class<LoginViewModel> {
		return LoginViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentLoginBinding {
		return FragmentLoginBinding.inflate(inflater!!)
	}

	override fun onLogInClick() {
		showToast("Login click")
		viewModel.submitForm()
	}
}