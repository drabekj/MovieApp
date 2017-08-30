package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.popular

import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.MovieServiceProvider
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageViewModel
import org.alfonz.utility.NetworkUtility
import org.alfonz.view.StatefulLayout


class PopularMoviesViewModel: MoviesPageViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// send request
		loadPopularMovies()
	}

	private fun loadPopularMovies() {
		if (NetworkUtility.isOnline(MoviesApplication.getContext())) {
			val callType = MovieServiceProvider.POPULAR_MOVIES_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				state.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = MovieServiceProvider.service.popularMovies()
				val callback = MovieCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}
}