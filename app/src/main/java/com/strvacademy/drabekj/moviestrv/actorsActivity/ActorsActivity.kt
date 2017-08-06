package com.strvacademy.drabekj.moviestrv.actorsActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity


class ActorsActivity : BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_actors)

		setupActionBar(INDICATOR_NONE, "Actors")
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_toolbar_menu, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item!!.itemId) {
			R.id.action_search -> {
				showToast("Search Action")
				return true
			}

			else -> return super.onOptionsItemSelected(item)
		}
	}
}