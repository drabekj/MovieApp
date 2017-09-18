package com.strvacademy.drabekj.moviestrv.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.strvacademy.drabekj.moviestrv.ui.actors.ActorsFragment
import com.strvacademy.drabekj.moviestrv.ui.movies.MoviesFragment
import com.strvacademy.drabekj.moviestrv.ui.profile.ProfileFragment


class MainActivity : BaseActivity() {
	var mCurrentFragment: Fragment? = null


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupBottomNavView()
		replaceFragment(MoviesFragment.TAG)
	}

	private fun setupBottomNavView() {
		bottom_navigation.setOnNavigationItemSelectedListener(
				{ item ->
					when (item.itemId) {
						R.id.action_movies -> {
							replaceFragment(MoviesFragment.TAG)
						}
						R.id.action_actors -> {
							replaceFragment(ActorsFragment.TAG)
						}
						R.id.action_profile -> {
							replaceFragment(ProfileFragment.TAG)
						}
					}
					true
				})
		// Ignore reselecting the same tab
		bottom_navigation.setOnNavigationItemReselectedListener { }
	}

	fun replaceFragment(tag: String) {
		if (mCurrentFragment != null) {
			this.supportFragmentManager.beginTransaction().detach(mCurrentFragment).commitAllowingStateLoss()
		}

		var fragment = this.supportFragmentManager.findFragmentByTag(tag)
		val transaction = this.supportFragmentManager.beginTransaction()

		if (fragment == null) {
			when (tag) {
				MoviesFragment.TAG -> fragment = MoviesFragment.newInstance()
				ActorsFragment.TAG -> fragment = ActorsFragment.newInstance()
				ProfileFragment.TAG -> fragment = ProfileFragment.newInstance()
				else -> {
				}
			}

			transaction.add(R.id.fragment_container, fragment, tag)
		} else {
			transaction.attach(fragment)
		}
		transaction.commitAllowingStateLoss()
		mCurrentFragment = fragment
	}
}