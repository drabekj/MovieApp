package com.strvacademy.drabekj.moviestrv.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.model.entity.AccountEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.AccountServiceProvider
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.strvacademy.drabekj.moviestrv.ui.actors.ActorsFragment
import com.strvacademy.drabekj.moviestrv.ui.movies.MoviesFragment
import com.strvacademy.drabekj.moviestrv.ui.profile.ProfileFragment
import com.strvacademy.drabekj.moviestrv.ui.startup.StartupActivity
import com.strvacademy.drabekj.moviestrv.utils.KeyStoreUtil
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.NetworkUtility
import retrofit2.Call
import retrofit2.Response


class MainActivity : BaseActivity() {
	var mCurrentFragment: Fragment? = null


	companion object {
		val EXTRA_KEY_SKIP_LOGIN = "EXTRA_KEY_SKIP_LOGIN"

		fun startAsIntent(context: Context, skipLogin: Boolean) {
			val extras = Bundle()

			extras.putBoolean(EXTRA_KEY_SKIP_LOGIN, skipLogin)

			val intent = Intent(context, MainActivity::class.java)
			intent.putExtras(extras)
			intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

			context.startActivity(intent)
		}
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		// show StartupActivity if user not logged in or isn't in guest session
		if (!MoviesApplication.isUserLoggedIn() && !intent.getBooleanExtra(EXTRA_KEY_SKIP_LOGIN, false))
			StartupActivity.startAsIntent(this)

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupBottomNavView()
		replaceFragment(MoviesFragment.TAG)
	}

	override fun onResume() {
		super.onResume()

		if (MoviesApplication.isUserLoggedIn() && MoviesApplication.accountID.get() == null)
			MainViewModel().loadAccountId()
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