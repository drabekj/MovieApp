package com.strvacademy.drabekj.movieapp.ui.movies.moviesPage

import android.databinding.ObservableArrayList
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseViewModel
import android.databinding.ObservableField
import org.alfonz.view.StatefulLayout
import com.strvacademy.drabekj.movieapp.model.entity.MovieEntity
import com.strvacademy.drabekj.movieapp.model.entity.MovieResultsEntity
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestResponseHandler
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

	inner class MoviesCallback(callManager: CallManager) : Callback<MovieResultsEntity>(callManager) {
		override fun onSuccess(call: Call<MovieResultsEntity>, response: Response<MovieResultsEntity>) {
			movies.clear()
			movies.addAll(response.body()!!.results!!)
			setState(movies)
		}

		override fun onError(call: Call<MovieResultsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
			setState(movies)
		}

		override fun onFail(call: Call<MovieResultsEntity>, throwable: Throwable) {
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