package com.strvacademy.drabekj.movieapp.ui.actors

import android.database.MatrixCursor
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.support.v4.widget.CursorAdapter
import com.strvacademy.drabekj.movieapp.MoviesApplication
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.listener.OnItemClickListener
import com.strvacademy.drabekj.movieapp.model.entity.ActorEntity
import com.strvacademy.drabekj.movieapp.model.entity.ActorsResultsEntity
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.movieapp.model.remote.rest.provider.ActorServiceProvider
import com.strvacademy.drabekj.movieapp.ui.movies.SearchActorResultsAdapter
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.rest.call.Callback
import org.alfonz.utility.NetworkUtility
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Response


class ActorsViewModel : BaseViewModel<ActorsView>() {
	val state = ObservableField<Int>()
	var searchResults = ObservableArrayList<ActorEntity>()

	val items: ObservableArrayList<ActorsItemViewModel> = ObservableArrayList()
	val onActorClickListener = OnItemClickListener<ActorsItemViewModel> { item -> view!!.onActorClick(item.actor.get()) }
	val itemBinding = ItemBinding.of<ActorsItemViewModel>(BR.itemViewModel, R.layout.fragment_actors_list_item)
			.bindExtra(BR.listener, onActorClickListener)!!

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())


	override fun onStart() {
		super.onStart()
		if (items.isEmpty())
			loadData()
	}

	private fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		loadPopularActors()
	}

	private fun loadPopularActors() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = ActorServiceProvider.POPULAR_ACTORS_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				state.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = ActorServiceProvider.service.popularActors()
				val callback = ActorsCallBack(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	inner class ActorsCallBack(callManager: CallManager) : Callback<ActorsResultsEntity>(callManager) {
		override fun onSuccess(call: Call<ActorsResultsEntity>, response: Response<ActorsResultsEntity>) {
			val actorsLimit = 7
			updateActors(response.body()!!.results!!.sliceArray(0..actorsLimit))

			setState(items)
		}

		override fun onError(call: Call<ActorsResultsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
			setState(items)
		}

		override fun onFail(call: Call<ActorsResultsEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
			setState(items)
		}
	}

	private fun setState(data: ObservableArrayList<ActorsItemViewModel>) {
		if (data.isNotEmpty()) {
			state.set(StatefulLayout.CONTENT)
		} else {
			state.set(StatefulLayout.EMPTY)
		}
	}

	private fun updateActors(a: Array<ActorEntity>) {
		items.clear()
		a.mapTo(items) { ActorsItemViewModel(it) }
	}


	//	SEARCH
	fun createSearchAdapter(): CursorAdapter {
		return SearchActorResultsAdapter(MoviesApplication.context, null, 0)
	}

	fun loadData(query: String?) {
		// send request
		if (query != null && query.isNotEmpty())
			loadSearchResults(query)
	}

	private fun loadSearchResults(query: String) {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = ActorServiceProvider.SEARCH_ACTOR_CALL_TYPE
			// enqueue call
			val call = ActorServiceProvider.service.searchActor(query)
			val callback = SearchResultsCallback(mCallManager)
			mCallManager.enqueueCall(call, callback, callType)
		}
	}

	inner class SearchResultsCallback(callManager: CallManager) : Callback<ActorsResultsEntity>(callManager) {
		override fun onSuccess(call: Call<ActorsResultsEntity>, response: Response<ActorsResultsEntity>) {
			searchResults.clear()
			searchResults.addAll(response.body()?.results!!)

			view?.showActorResults(createResultsCursor())
		}

		override fun onError(call: Call<ActorsResultsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<ActorsResultsEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}

	fun createResultsCursor(): MatrixCursor {
		val fields = arrayOf(
				SearchActorResultsAdapter.RESULT_COLUMN_ID,
				SearchActorResultsAdapter.RESULT_COLUMN_NAME,
				SearchActorResultsAdapter.RESULT_COLUMN_PROFILE_PATH)
		val cursor = MatrixCursor(fields)
		val temp = arrayOfNulls<String>(fields.size)
		for (item in searchResults) {
			temp[0] = Integer.toString(item.id!!)
			temp[1] = item.name
			temp[2] = item.profilePath
			cursor.addRow(temp)
		}

		return cursor
	}
}