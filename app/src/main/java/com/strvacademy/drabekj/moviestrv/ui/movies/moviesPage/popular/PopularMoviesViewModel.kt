package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.popular

import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.MoviesDataResponse
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiProvider
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageViewModel
import org.alfonz.utility.Logcat
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularMoviesViewModel: MoviesPageViewModel(), MoviesPageViewModel.onLoadDataListener {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		dataSource.getPopularMovies(this)
	}

	override fun successLoadingData(m : List<Movie>) {
		onLoadData(m)
	}

	override fun errorLoadingData() {
		errorLoadingData()
	}
}