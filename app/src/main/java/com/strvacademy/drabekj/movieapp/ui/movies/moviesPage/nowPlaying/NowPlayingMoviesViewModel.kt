package com.strvacademy.drabekj.movieapp.ui.movies.moviesPage.nowPlaying

import com.strvacademy.drabekj.movieapp.MoviesApplication
import com.strvacademy.drabekj.movieapp.model.remote.rest.provider.MovieServiceProvider
import com.strvacademy.drabekj.movieapp.ui.movies.moviesPage.MoviesPageViewModel
import org.alfonz.utility.NetworkUtility
import org.alfonz.view.StatefulLayout


class NowPlayingMoviesViewModel : MoviesPageViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// send request
		loadNowPlayingMovies()
	}

	private fun loadNowPlayingMovies() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = MovieServiceProvider.NOW_PLAYING_MOVIES_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				state.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = MovieServiceProvider.service.nowPlayingMovies()
				val callback = MoviesCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}
}