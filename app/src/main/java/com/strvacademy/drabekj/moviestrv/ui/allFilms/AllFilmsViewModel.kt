package com.strvacademy.drabekj.moviestrv.ui.allFilms

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.listener.OnItemClickListener
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiProvider
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.alfonz.utility.Logcat
import org.alfonz.view.StatefulLayout

class AllFilmsViewModel : BaseViewModel<AllFilmsView>() {
	val state = ObservableField<Int>()
	var actorId: Int? = null
	val movies: ObservableList<FilmItemViewModel> = ObservableArrayList()
	val onItemClickListener = OnItemClickListener<FilmItemViewModel> {
		item -> Toast.makeText(MoviesApplication.getContext(), "click " + item.movie.get().name, Toast.LENGTH_SHORT).show()
	}
	val itemBindingCast = ItemBinding.of<FilmItemViewModel>(BR.itemViewModel, R.layout.fragment_all_films_movie_list_item)
			.bindExtra(BR.listener, onItemClickListener)!!

	val dataSource: MovieDataSource = MovieRepository(TheMovieDbApiProvider.newInstance()!!, MovieDummyData())


	val profileID = 1


	override fun onStart() {
		Logcat.d("allMovies fragment -> actorId = " + actorId)

		super.onStart()
		if (movies.isEmpty())
			loadData(profileID)
	}

	private fun loadData(id: Int) {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getFavoriteMoviesByProfile(id))
	}

	private fun  onLoadData(m: Array<Movie>) {
		updateMovies(m)

		// show content
		if(!movies.isEmpty())
			state.set(StatefulLayout.CONTENT)
		else
			state.set(StatefulLayout.EMPTY)
	}

	private fun updateMovies(m: Array<Movie>) {
		movies.clear()
		m.mapTo(movies) { FilmItemViewModel(it) }
	}
}