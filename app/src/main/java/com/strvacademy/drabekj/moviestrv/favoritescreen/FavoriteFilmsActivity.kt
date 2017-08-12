package com.strvacademy.drabekj.moviestrv.favoritescreen

import android.os.Bundle
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity

class FavoriteFilmsActivity: BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_favorite_films)

		setupToolbar()
	}

	private fun setupToolbar() {
		setupActionBar(INDICATOR_BACK, getString(R.string.favorite_films))
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}
}