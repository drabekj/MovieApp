package com.strvacademy.drabekj.moviestrv.ui.movies

import android.database.MatrixCursor
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.support.v4.widget.CursorAdapter
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.model.entity.MovieResultsEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.MovieServiceProvider
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.rest.call.Callback
import org.alfonz.utility.NetworkUtility
import retrofit2.Call
import retrofit2.Response


class MoviesViewModel : BaseViewModel<MoviesView>() {
	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())
	var searchResults = ObservableArrayList<MovieEntity>()

	fun createSearchAdapter(): CursorAdapter {
		return SearchResultAdapter(MoviesApplication.getContext(), null, 0)
	}

	fun loadData(query: String?) {
		// send request
		if (query != null && query.isNotEmpty())
			loadSearchResults(query)
	}

	private fun loadSearchResults(query: String) {
		if (NetworkUtility.isOnline(MoviesApplication.getContext())) {
			val callType = MovieServiceProvider.SEARCH_MOVIE_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = MovieServiceProvider.service.searchMovie(query)
				val callback = SearchResultsCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		}
	}

	inner class SearchResultsCallback(callManager: CallManager) : Callback<MovieResultsEntity>(callManager) {
		override fun onSuccess(call: Call<MovieResultsEntity>, response: Response<MovieResultsEntity>) {
			searchResults.clear()
			searchResults.addAll(response.body()?.results!!)

			view?.showMovieResults(createResultsCursor())
		}

		override fun onError(call: Call<MovieResultsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<MovieResultsEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}

	fun createResultsCursor(): MatrixCursor {
		var data = searchResults

		val cursor = MatrixCursor(arrayOf("_id", "text"))
		val temp = arrayOfNulls<String>(2)
		var id = 0
		for (item in data) {
			temp[0] = Integer.toString(id++)
			temp[1] = item.title
			cursor.addRow(temp)
		}

		return cursor
	}
}