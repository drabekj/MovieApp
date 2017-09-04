package com.strvacademy.drabekj.moviestrv.ui.actordetail

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.entity.ActorEntity
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.ActorServiceProvider
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.NetworkUtility
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Response

class ActorDetailViewModel: BaseViewModel<ActorDetailView>() {
	val state = ObservableField<Int>()
	val actor = ObservableField<ActorEntity>()
	val knownForMovies = ObservableArrayList<MovieEntity>()
	var id: Int? = null

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())


	override fun onStart() {
		super.onStart()
		if (actor.get() == null)
			loadData(id!!)
	}

	private fun loadData(id: Int) {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		loadActor(id)
	}

	private fun loadActor(id: Int) {
		if (NetworkUtility.isOnline(MoviesApplication.getContext())) {
			val callType = ActorServiceProvider.ACTOR_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				state.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = ActorServiceProvider.service.actor(id)
				val callback = ActorCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	inner class ActorCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<ActorEntity>(callManager) {
		override fun onSuccess(call: Call<ActorEntity>, response: Response<ActorEntity>) {
			actor.set(response.body())

//			loadKnownFor(id)

			setState(actor)
		}

		override fun onError(call: Call<ActorEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
			setState(actor)
		}

		override fun onFail(call: Call<ActorEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
			setState(actor)
		}
	}

	private fun setState(data: ObservableField<ActorEntity>) {
		if (data.get() != null) {
			state.set(StatefulLayout.CONTENT)
		} else {
			state.set(StatefulLayout.EMPTY)
		}
	}

//	private fun updateCast(data: Array<MovieEntity>) {
//		knownForMovies.clear()
//		data.mapTo(knownForMovies) { ActorMovieItemViewModel(it) }
//	}
}