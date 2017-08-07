package com.strvacademy.drabekj.moviestrv.mainscreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.strvacademy.drabekj.moviestrv.actorsscreen.ActorsFragment
import com.strvacademy.drabekj.moviestrv.moviesscreen.MoviesFragment
import com.strvacademy.drabekj.moviestrv.profilescreen.ProfileFragment


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupBottomNavView(savedInstanceState)
		showFragmentInTab(savedInstanceState, MoviesFragment.newInstance())
	}

//	TODO ? How to ignore click if it's on the same item?
//	TODO ?!! Doesn't work onConfigurationChange
	private fun setupBottomNavView(savedInstanceState: Bundle?) {
		bottom_navigation.setOnNavigationItemSelectedListener(
				{ item ->
					when (item.itemId) {
						R.id.action_movies -> {
							showFragmentInTab(savedInstanceState, MoviesFragment.newInstance())
						}
						R.id.action_actors -> {
							showFragmentInTab(savedInstanceState, ActorsFragment.newInstance())
						}
						R.id.action_profile -> {
							showFragmentInTab(savedInstanceState, ProfileFragment.newInstance())
						}
					}
					true
				})
	}

	fun showFragmentInTab(savedInstanceState: Bundle?, fragment: Fragment) {
		if (findViewById<View>(R.id.fragment_container) != null && savedInstanceState == null) {
			supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
		}
	}
}
