package com.strvacademy.drabekj.movieapp.ui.startup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseActivity

class StartupActivity: BaseActivity() {

	companion object {
		fun startAsIntent(context: Context) {
			val intent = Intent(context, StartupActivity::class.java)
			context.startActivity(intent)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_startup)
	}
}