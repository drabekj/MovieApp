package com.strvacademy.drabekj.moviestrv.ui.profile

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.view.View
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.Profile
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiProvider
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseViewModel
import org.alfonz.view.StatefulLayout

class ProfileViewModel: BaseViewModel<ProfileView>() {
	val state = ObservableField<Int>()
	val stateContent = ObservableField<Int>()
	val profile = ObservableField<Profile>()
	val favMovies = ObservableArrayList<String>()

	val dataSource: MovieDataSource = MovieRepository(TheMovieDbApiProvider.newInstance()!!, MovieDummyData())


	override fun onStart() {
		super.onStart()

		if (!MoviesApplication.isUserLoggedIn()) {
			state.set(StatefulLayout.EMPTY)
			view?.setupLoggedOutState()
		}

		if (profile.get() == null)
			loadData()
	}

	private fun loadData() {
		// show progress
		stateContent.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getProfile())
	}

	private fun  onLoadData(p: Profile?) {
		profile.set(p)

		favMovies.clear()
		favMovies.addAll(p!!.favMoviesArray)

		// show content
		if(profile.get() != null)
			stateContent.set(StatefulLayout.CONTENT)
		else
			stateContent.set(StatefulLayout.EMPTY)
	}
}