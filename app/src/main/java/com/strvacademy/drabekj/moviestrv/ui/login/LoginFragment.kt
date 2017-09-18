package com.strvacademy.drabekj.moviestrv.ui.login

import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentLoginBinding
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseFragment
import android.app.ProgressDialog
import android.content.Context
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.ui.main.MainActivity


class LoginFragment: BaseFragment<LoginView, LoginViewModel, FragmentLoginBinding>(), LoginView {

	var progressDialog: ProgressDialog? = null


	override fun onAttach(context: Context?) {
		super.onAttach(context)
		progressDialog = ProgressDialog(activity, R.style.AuthenticationProgressDialog)
	}

	override fun getViewModelClass(): Class<LoginViewModel> {
		return LoginViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentLoginBinding {
		return FragmentLoginBinding.inflate(inflater!!)
	}

	override fun onLogInClick() {
		viewModel.submitForm()
	}

	override fun showLoadingDialog() {
		progressDialog?.isIndeterminate = true
		progressDialog?.setMessage(getString(R.string.authenticating))
		progressDialog?.show()
	}

	override fun dismissLoadingDialog() {
		progressDialog?.dismiss()
	}

	override fun onLoginSuccessful() {
		MainActivity.startAsIntent(activity)
	}
}