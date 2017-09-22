package com.strvacademy.drabekj.moviestrv.ui.movies

import android.database.MatrixCursor
import android.databinding.ObservableArrayList
import android.support.v4.widget.CursorAdapter
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.model.entity.MovieResultsEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.MovieServiceProvider
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseViewModel
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
		return SearchResultAdapter(MoviesApplication.context, null, 0)
	}

	fun loadData(query: String?) {
		// send request
		if (query != null && query.isNotEmpty())
			loadSearchResults(query)
	}

	private fun loadSearchResults(query: String) {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = MovieServiceProvider.SEARCH_MOVIE_CALL_TYPE
			// enqueue call
			val call = MovieServiceProvider.service.searchMovie(query)
			val callback = SearchResultsCallback(mCallManager)
			mCallManager.enqueueCall(call, callback, callType)
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
		val fields = arrayOf(
				SearchResultAdapter.RESULT_COLUMN_ID,
				SearchResultAdapter.RESULT_COLUMN_TITLE,
				SearchResultAdapter.RESULT_COLUMN_POSTER_PATH,
				SearchResultAdapter.RESULT_COLUMN_RELEASE_DATE,
				SearchResultAdapter.RESULT_COLUMN_VOTE_AVERAGE)
		val cursor = MatrixCursor(fields)
		val temp = arrayOfNulls<String>(fields.size)
		for (item in searchResults) {
			temp[0] = Integer.toString(item.id!!)
			temp[1] = item.title
			temp[2] = item.posterPath
			temp[3] = item.releaseDate
			temp[4] = item.voteAverage.toString()
			cursor.addRow(temp)
		}

		return cursor
	}
}