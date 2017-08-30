package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage

import android.databinding.ObservableArrayList
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.listener.OnLoadDataListener
import org.alfonz.view.StatefulLayout
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.model.entity.ResultsEntity
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiProvider
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.rest.call.Callback
import retrofit2.Call
import retrofit2.Response


abstract class MoviesPageViewModel : BaseViewModel<MoviesPageView>() {
	val state = ObservableField<Int>()
	var movies = ObservableArrayList<MovieEntity>()

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())


	override fun onStart() {
		super.onStart()
		if (movies.isEmpty())
			loadData()
	}

	abstract fun loadData()

	inner class MoviesCallback(callManager: CallManager) : Callback<ResultsEntity>(callManager) {
		override fun onSuccess(call: Call<ResultsEntity>, response: Response<ResultsEntity>) {
			movies.clear()
			movies.addAll(response.body()!!.results!!)
			setState(movies)
		}

		override fun onError(call: Call<ResultsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
			setState(movies)
		}

		override fun onFail(call: Call<ResultsEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
			setState(movies)
		}
	}

	private fun setState(data: ObservableArrayList<MovieEntity>) {
		if (data.isNotEmpty()) {
			state.set(StatefulLayout.CONTENT)
		} else {
			state.set(StatefulLayout.EMPTY)
		}
	}
}