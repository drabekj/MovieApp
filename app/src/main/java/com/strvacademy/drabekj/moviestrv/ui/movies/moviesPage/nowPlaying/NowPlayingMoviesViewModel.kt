package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.nowPlaying

import com.strvacademy.drabekj.moviestrv.model.MoviesDataResponse
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiProvider
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageViewModel
import org.alfonz.utility.Logcat
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NowPlayingMoviesViewModel : MoviesPageViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		retrofitRequest()
	}

	private fun retrofitRequest() {
		TheMovieDbApiProvider.newInstance()!!.getNowPlayingMovies().enqueue(object : Callback<MoviesDataResponse> {
			override fun onFailure(call: Call<MoviesDataResponse>?, t: Throwable?) {
				Logcat.d("retrofit fail |" + t.toString())
				onErrorLoadingData()
			}

			override fun onResponse(call: Call<MoviesDataResponse>?, response: Response<MoviesDataResponse>?) {
				Logcat.d("retrofit successLoadingData: " + response!!.toString())
				onLoadData(response.body()!!.results)
			}
		})
	}
}