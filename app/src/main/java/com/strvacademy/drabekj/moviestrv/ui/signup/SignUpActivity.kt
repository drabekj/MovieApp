package com.strvacademy.drabekj.moviestrv.ui.signup

import android.os.Bundle
import android.view.View
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseActivity
import android.webkit.WebView


class SignUpActivity : BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_signup)

		setupToolbar()
		loadWeb()
	}

	private fun setupToolbar() {
		setupActionBar(INDICATOR_BACK, getString(R.string.sign_up))
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}

	fun loadWeb() {
		val myWebView = findViewById<View>(R.id.webview) as WebView
		myWebView.settings.javaScriptEnabled = true
		myWebView.loadUrl("https://www.themoviedb.org/account/signup")
	}
}