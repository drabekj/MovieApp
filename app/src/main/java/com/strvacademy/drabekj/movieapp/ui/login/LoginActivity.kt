package com.strvacademy.drabekj.movieapp.ui.login

import android.os.Bundle
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseActivity

class LoginActivity: BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		setupToolbar()
	}

	private fun setupToolbar() {
		setupActionBar(INDICATOR_BACK, getString(R.string.log_in))
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}
}