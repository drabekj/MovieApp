package com.strvacademy.drabekj.moviestrv.actordetailscreen

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.view.StatefulLayout

class ActorDetailViewModel: BaseViewModel<ActorDetailView>() {
	val state = ObservableField<Int>()
	val actor = ObservableField<Actor>()
	val knownForMovies = ObservableArrayList<String>()
	var id: Int? = null

	val dataSource: MovieDataSource = MovieRepository(MovieDummyData())


	override fun onStart() {
		super.onStart()
		if (actor.get() == null)
			loadData(id!!)
	}

	private fun loadData(id: Int) {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getActorById(id))
	}

	private fun  onLoadData(a: Actor?) {
		actor.set(a)

		knownForMovies.clear()
		knownForMovies.addAll(a!!.moviesArray)

		// show content
		if(actor.get() != null)
			state.set(StatefulLayout.CONTENT)
		else
			state.set(StatefulLayout.EMPTY)
	}
}