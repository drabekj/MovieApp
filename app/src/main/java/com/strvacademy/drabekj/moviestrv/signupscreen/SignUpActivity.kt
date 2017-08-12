package com.strvacademy.drabekj.moviestrv.signupscreen

import android.os.Bundle
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity

class SignUpActivity: BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_signup)

		setupToolbar()
	}

	private fun setupToolbar() {
		setupActionBar(INDICATOR_BACK, getString(R.string.sign_up))
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}

}