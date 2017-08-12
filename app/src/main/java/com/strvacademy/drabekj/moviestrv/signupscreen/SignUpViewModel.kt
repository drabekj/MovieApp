package com.strvacademy.drabekj.moviestrv.signupscreen

import android.databinding.ObservableField
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.utility.Logcat

class SignUpViewModel: BaseViewModel<SignUpView>() {
	val state = ObservableField<Int>()
	val username = ObservableField<String>()
	val email = ObservableField<String>()
	val password = ObservableField<String>()
	val confirmPassword = ObservableField<String>()

	fun submitForm() {
		if (!checkUsername()) {
			Toast.makeText(MoviesApplication.getContext(), "username error", Toast.LENGTH_SHORT).show()
			return
		}
		if (!checkEmail()) {
			Toast.makeText(MoviesApplication.getContext(), "email error", Toast.LENGTH_SHORT).show()
			return
		}
		if (!checkPassword()) {
			Toast.makeText(MoviesApplication.getContext(), "password error", Toast.LENGTH_SHORT).show()
			return
		}
	}

	fun checkUsername(): Boolean {
		Logcat.d("username:" + username.get())
		val u = username.get()

		if (u == null || u.isEmpty() || u.length < 4) {
			view!!.showError(FieldCheck.USERNAME,true)
			return false
		}

		view!!.showError(FieldCheck.USERNAME,false)
		return true
	}

	fun checkEmail(): Boolean {
		Logcat.d("email:" + email.get())
		val e = email.get()

		if (e == null || e.isEmpty() || e.length < 4 || !e.contains("@")) {
			view!!.showError(FieldCheck.EMAIL,true)
			return false
		}

		view!!.showError(FieldCheck.EMAIL,false)
		return true
	}

	fun checkPassword(): Boolean {
		Logcat.d("password:" + password.get() + " confirmPassword:" + confirmPassword.get())
		val pwd = password.get()
		val cPwd = confirmPassword.get()

		if (pwd == null || cPwd == null || pwd.isEmpty() || cPwd.isEmpty() || !pwd.equals(cPwd) || pwd.length < 8) {
			view!!.showError(FieldCheck.PASSWORD,true)
			return false
		}

		view!!.showError(FieldCheck.PASSWORD,false)
		return true
	}


	companion object {
		enum class FieldCheck {
			USERNAME, EMAIL, PASSWORD
		}
	}
}