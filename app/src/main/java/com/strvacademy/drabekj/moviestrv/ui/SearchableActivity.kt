package com.strvacademy.drabekj.moviestrv.ui

import com.strvacademy.drabekj.moviestrv.utils.BaseActivity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import com.strvacademy.drabekj.moviestrv.R
import org.alfonz.utility.Logcat


class SearchableActivity: BaseActivity() {

	public override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.search_view_layout)

		// Get the intent, verify the action and get the query
		handleIntent(intent)
	}

	override fun onNewIntent(intent: Intent?) {
		super.onNewIntent(intent)

		handleIntent(intent!!)
	}

	private fun handleIntent(intent: Intent) {
		if (Intent.ACTION_SEARCH.equals(intent.action)) {
			val query = intent.getStringExtra(SearchManager.QUERY)

			// do my search
			Logcat.d("search " + query)
		}
	}

}