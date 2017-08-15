package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.popular

import com.strvacademy.drabekj.moviestrv.model.PopularMoviesDataResponse
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiService
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageViewModel
import org.alfonz.utility.Logcat
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularMoviesViewModel: MoviesPageViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		retrofitRequest()
	}

	private fun retrofitRequest() {
		TheMovieDbApiService.newInstance()!!.getPopularMovies().enqueue(object : Callback<PopularMoviesDataResponse> {
			override fun onFailure(call: Call<PopularMoviesDataResponse>?, t: Throwable?) {
				Logcat.d("retrofit fail |" + t.toString())
			}

			override fun onResponse(call: Call<PopularMoviesDataResponse>?, response: Response<PopularMoviesDataResponse>?) {
				Logcat.d("retrofit success: " + response!!.body())
				onLoadData(response.body()!!.results)
			}
		})
	}
}