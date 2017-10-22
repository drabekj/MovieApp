package com.strvacademy.drabekj.movieapp.ui.login

import android.os.Bundle
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseActivity

class LoginActivity: BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		// Metrics
		loginAccessedMetric()

		setupToolbar()
	}

	private fun setupToolbar() {
		setupActionBar(INDICATOR_BACK, getString(R.string.log_in))
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}

	private fun loginAccessedMetric() {
		Answers.getInstance().logContentView(ContentViewEvent()
				.putContentType("screen")
				.putContentName("Login"))
	}
}