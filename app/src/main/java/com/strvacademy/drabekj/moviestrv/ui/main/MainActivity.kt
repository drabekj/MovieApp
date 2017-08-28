package com.strvacademy.drabekj.moviestrv.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.model.MoviesDataResponse
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiProvider
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.strvacademy.drabekj.moviestrv.ui.actors.ActorsFragment
import com.strvacademy.drabekj.moviestrv.ui.movies.MoviesFragment
import com.strvacademy.drabekj.moviestrv.ui.profile.ProfileFragment
import org.alfonz.utility.Logcat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupBottomNavView()
		showFragmentInTab(MoviesFragment.newInstance())

		retrofitNew()
	}

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
		// Ignore reselecting the same tab
		bottom_navigation.setOnNavigationItemReselectedListener { }
	}

	fun showFragmentInTab(fragment: Fragment) {
		if (findViewById<View>(R.id.fragment_container) != null) {
			supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
		}
	}

	private fun retrofitNew() {
		TheMovieDbApiProvider.newInstance()!!.getPopularMovies().enqueue(object : Callback<MoviesDataResponse> {
			override fun onFailure(call: Call<MoviesDataResponse>?, t: Throwable?) {
				showToast("Fail")
				Logcat.d("retrofit fail |" + t.toString())
			}

			override fun onResponse(call: Call<MoviesDataResponse>?, response: Response<MoviesDataResponse>?) {
				showToast("Success")
				Logcat.d("retrofit successLoadingData: " + response!!.body())
			}
		})
	}
}