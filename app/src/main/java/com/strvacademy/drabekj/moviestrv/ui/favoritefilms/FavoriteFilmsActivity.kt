package com.strvacademy.drabekj.moviestrv.ui.favoritefilms

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity

class FavoriteFilmsActivity: BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_favorite_films)

		setupToolbar()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.toolbar_favorite_films, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item!!.itemId) {
			R.id.action_edit -> {
				showToast("Edit action")
				return true
			}

			R.id.action_share -> {
				showToast("Share action")
				return true
			}

			else -> return super.onOptionsItemSelected(item)
		}
	}

	private fun setupToolbar() {
		setupActionBar(INDICATOR_BACK, getString(R.string.favorite_films))
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}
}