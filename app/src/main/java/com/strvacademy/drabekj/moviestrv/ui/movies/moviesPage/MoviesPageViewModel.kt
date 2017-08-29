package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage

import android.databinding.ObservableArrayList
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.listener.OnLoadDataListener
import org.alfonz.view.StatefulLayout
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiProvider


abstract class MoviesPageViewModel : BaseViewModel<MoviesPageView>(), OnLoadDataListener<List<Movie>> {
    val state = ObservableField<Int>()
    var movies = ObservableArrayList<Movie>()

    val dataSource: MovieDataSource = MovieRepository(TheMovieDbApiProvider.newInstance()!!, MovieDummyData())


    override fun onStart() {
        super.onStart()
        if (movies.isEmpty())
            loadData()
    }

	abstract fun loadData()

	override fun errorLoadingData() {
		state.set(StatefulLayout.EMPTY)
	}

	override fun onLoadData(m: List<Movie>) {
        // save data
        movies.clear()
        movies.addAll(m)

        // show content
        if (movies.isEmpty())
            state.set(StatefulLayout.EMPTY)
        else
            state.set(StatefulLayout.CONTENT)
    }
}