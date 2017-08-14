package com.strvacademy.drabekj.moviestrv.ui.actors

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.listener.OnItemClickListener
import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.alfonz.view.StatefulLayout


class ActorsViewModel: BaseViewModel<ActorsView>() {
	val state = ObservableField<Int>()

	val items: ObservableArrayList<ActorsItemViewModel> = ObservableArrayList()
	val onActorClickListener = OnItemClickListener<ActorsItemViewModel> { item -> view!!.onActorClick(item.actor.get()) }
	val itemBinding = ItemBinding.of<ActorsItemViewModel>(BR.itemViewModel, R.layout.fragment_actors_list_item)
			.bindExtra(BR.listener, onActorClickListener)!!

	val dataSource: MovieDataSource = MovieRepository(MovieDummyData())


	override fun onStart() {
		super.onStart()
		if (items.isEmpty())
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
		updateActors(popularActors)

		// show content
		if (items.isEmpty())
			state.set(StatefulLayout.EMPTY)
		else
			state.set(StatefulLayout.CONTENT)
	}

	private fun updateActors(a: Array<Actor>) {
		items.clear()
		a.mapTo(items) { ActorsItemViewModel(it) }
	}
}