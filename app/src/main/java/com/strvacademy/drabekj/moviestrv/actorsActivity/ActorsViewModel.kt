package com.strvacademy.drabekj.moviestrv.actorsActivity

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.view.StatefulLayout


class ActorsViewModel: BaseViewModel<ActorsView>() {
	val state = ObservableField<Int>()
	var actors = ObservableArrayList<Actor>()

	val dataSource: MovieDataSource = MovieRepository(MovieDummyData())


	override fun onStart() {
		super.onStart()
		if (actors.isEmpty())
			loadData()
	}

	private fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getPopularActors())
	}

	private fun onLoadData(popularActors: Array<Actor>) {
		// save data
		// TODO do better (observable array replacement)
		actors.clear()
		actors.addAll(popularActors)

		// show content
		if (actors.isEmpty())
			state.set(StatefulLayout.EMPTY)
		else
			state.set(StatefulLayout.CONTENT)
	}
}