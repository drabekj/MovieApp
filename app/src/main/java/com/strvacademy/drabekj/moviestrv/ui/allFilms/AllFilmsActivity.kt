package com.strvacademy.drabekj.moviestrv.ui.allFilms

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.ui.actordetail.ActorDetailFragment
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseActivity

class AllFilmsActivity : BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_all_films)

		setupToolbar()
		setupFragment(savedInstanceState)
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

	private fun setupFragment(savedInstanceState: Bundle?) {
		if (findViewById<View>(R.id.fragment_container) != null && savedInstanceState == null) {
			// get actorId from bundle
			val actorId = intent.getIntExtra(ActorDetailFragment.EXTRA_KEY_ACTOR_ID, -1)

			// setup fragment
			val allMoviesFragment: AllFilmsFragment = AllFilmsFragment.newInstance(actorId)
			supportFragmentManager.beginTransaction().add(R.id.fragment_container, allMoviesFragment).commit()
		}
	}
}