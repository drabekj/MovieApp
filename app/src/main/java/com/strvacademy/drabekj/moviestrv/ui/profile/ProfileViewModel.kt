package com.strvacademy.drabekj.moviestrv.ui.profile

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.Profile
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.view.StatefulLayout

class ProfileViewModel: BaseViewModel<ProfileView>() {
	val state = ObservableField<Int>()
	val profile = ObservableField<Profile>()
	var favMovies = ObservableArrayList<String>()

	val dataSource: MovieDataSource = MovieRepository(MovieDummyData())


	override fun onStart() {
		super.onStart()
		if (profile.get() == null)
			loadData()
	}

	private fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getProfile())
	}

	private fun  onLoadData(p: Profile?) {
		profile.set(p)

		favMovies.clear()
		favMovies.addAll(p!!.favMoviesArray)

		// show content
		if(profile.get() != null)
			state.set(StatefulLayout.CONTENT)
		else
			state.set(StatefulLayout.EMPTY)
	}
}