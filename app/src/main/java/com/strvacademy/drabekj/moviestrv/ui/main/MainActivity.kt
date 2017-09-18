package com.strvacademy.drabekj.moviestrv.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.strvacademy.drabekj.moviestrv.ui.actors.ActorsFragment
import com.strvacademy.drabekj.moviestrv.ui.movies.MoviesFragment
import com.strvacademy.drabekj.moviestrv.ui.profile.ProfileFragment
import com.strvacademy.drabekj.moviestrv.utils.KeyStoreUtil
import cz.koto.keystorecompat.KeystoreCompat
import cz.koto.keystorecompat.exception.ForceLockScreenKitKatException
import cz.koto.keystorecompat.exception.ForceLockScreenMarshmallowException
import cz.koto.keystorecompat.utility.forceAndroidAuth
import org.alfonz.utility.Logcat


class MainActivity : BaseActivity() {
	var mCurrentFragment: Fragment? = null


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupBottomNavView()
		replaceFragment(MoviesFragment.TAG)







		Logcat.d("is KeystoreCompat available? --> " + KeystoreCompat.isKeystoreCompatAvailable().toString())
		Logcat.d("is isSecurityEnabled()? --> " + KeystoreCompat.isSecurityEnabled().toString())
		Logcat.d("is hasSecretLoadable()? --> " + KeystoreCompat.hasSecretLoadable().toString())

		KeystoreCompat.storeSecret(
				"Ahoj Honzo!",
				{
					Logcat.e("Store credentials failed!", it)
					if (it is ForceLockScreenMarshmallowException) {
						forceAndroidAuth(getString(R.string.kc_lock_screen_title), getString(R.string.kc_lock_screen_description),
								{ intent -> this.startActivityForResult(intent, FORCE_ENCRYPTION_REQUEST_M) }, KeystoreCompat.context)
					}
				},
				{ Logcat.d("Credentials stored.") })

		Logcat.d("is hasSecretLoadable()? --> " + KeystoreCompat.hasSecretLoadable().toString())
		KeystoreCompat.loadSecretAsString(
				{ secret -> Logcat.d("loaded successfully: " + secret) },
				{ exception ->
					if (exception is ForceLockScreenKitKatException) {
						this.startActivityForResult(exception.lockIntent, FORCE_SIGNUP_REQUEST)
					} else {
						Logcat.e(exception, "")
						forceAndroidAuth(getString(R.string.kc_lock_screen_title), getString(R.string.kc_lock_screen_description),
								{ intent -> this.startActivityForResult(intent, FORCE_SIGNUP_REQUEST) },
								KeystoreCompat.context)
					}
				},
				false)
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

							Logcat.d("is hasSecretLoadable()? --> " + KeystoreCompat.hasSecretLoadable().toString())
							KeystoreCompat.loadSecretAsString(
									{ secret -> Logcat.d("loaded successfully: " + secret) },
									{ exception ->
										if (exception is ForceLockScreenKitKatException) {
											this.startActivityForResult(exception.lockIntent, FORCE_SIGNUP_REQUEST)
										} else {
											Logcat.e(exception, "")
											forceAndroidAuth(getString(R.string.kc_lock_screen_title), getString(R.string.kc_lock_screen_description),
													{ intent -> this.startActivityForResult(intent, FORCE_SIGNUP_REQUEST) },
													KeystoreCompat.context)
										}
									},
									false)
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

	companion object {
		val FORCE_ENCRYPTION_REQUEST_M = 1112
		val FORCE_SIGNUP_REQUEST = 1111
	}
}