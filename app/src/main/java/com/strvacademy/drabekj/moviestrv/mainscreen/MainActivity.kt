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

		setupBottomNavView()
		showFragmentInTab(MoviesFragment.newInstance())
	}

//	TODO ? How to ignore click if it's on the same item?
	private fun setupBottomNavView() {
		bottom_navigation.setOnNavigationItemSelectedListener(
				{ item ->
					when (item.itemId) {
						R.id.action_movies -> {
							showFragmentInTab(MoviesFragment.newInstance())
						}
						R.id.action_actors -> {
							showFragmentInTab(ActorsFragment.newInstance())
						}
						R.id.action_profile -> {
							showFragmentInTab(ProfileFragment.newInstance())
						}
					}
					true
				})
	}

	fun showFragmentInTab(fragment: Fragment) {
		if (findViewById<View>(R.id.fragment_container) != null) {
			supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
		}
	}
}
