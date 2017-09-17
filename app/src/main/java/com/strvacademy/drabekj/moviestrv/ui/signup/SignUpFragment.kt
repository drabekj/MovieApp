package com.strvacademy.drabekj.moviestrv.ui.signup

import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentSignupBinding
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseFragment
import com.strvacademy.drabekj.moviestrv.ui.signup.SignUpViewModel.Companion.FieldCheck
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment: BaseFragment<SignUpView, SignUpViewModel, FragmentSignupBinding>(), SignUpView {

	override fun getViewModelClass(): Class<SignUpViewModel> {
		return SignUpViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentSignupBinding {
		return FragmentSignupBinding.inflate(inflater!!)
	}

	override fun onSignUpClick() {
		showToast("Validate and signUp")
		viewModel.submitForm()
	}

	// TODO get layout refs from binding
	override fun showError(field: FieldCheck, enabled: Boolean) {
		when(field) {
			FieldCheck.USERNAME -> {
				if (enabled) {
					signup_input_layout_username.isErrorEnabled = true
					signup_input_layout_username.error = getString(R.string.err_msg_username)
				}
				else
					signup_input_layout_username.isErrorEnabled = false
			}

			FieldCheck.EMAIL -> {
				if (enabled) {
					signup_input_layout_email.isErrorEnabled = true
					signup_input_layout_email.error = getString(R.string.err_msg_email)
				}
				else
					signup_input_layout_email.isErrorEnabled = false
			}

			FieldCheck.PASSWORD -> {
				if (enabled) {
					signup_input_layout_password.isErrorEnabled = true
					signup_input_layout_confirm_password.isErrorEnabled = true
					signup_input_layout_password.error = getString(R.string.err_msg_password)
					signup_input_layout_confirm_password.error = getString(R.string.err_msg_confirm_password)
				}
				else {
					signup_input_layout_password.isErrorEnabled = false
					signup_input_layout_confirm_password.isErrorEnabled = false
				}
			}
		}
	}
}