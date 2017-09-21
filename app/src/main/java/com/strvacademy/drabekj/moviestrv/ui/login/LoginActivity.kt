package com.strvacademy.drabekj.moviestrv.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseActivity

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