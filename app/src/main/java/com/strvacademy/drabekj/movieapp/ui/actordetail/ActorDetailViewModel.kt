package com.strvacademy.drabekj.movieapp.ui.actordetail

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.strvacademy.drabekj.movieapp.MoviesApplication
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.listener.OnItemClickListener
import com.strvacademy.drabekj.movieapp.model.entity.ActorEntity
import com.strvacademy.drabekj.movieapp.model.entity.CastEntity
import com.strvacademy.drabekj.movieapp.model.entity.CreditsEntity
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.movieapp.model.remote.rest.provider.ActorServiceProvider
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.NetworkUtility
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Response

class ActorDetailViewModel : BaseViewModel<ActorDetailView>() {
	val state = ObservableField<Int>()
	var id: Int? = null
	val actor = ObservableField<ActorEntity>()
	val moviesCount = ObservableField<Int>()
	val knownForMovies: ObservableList<ActorMovieItemViewModel> = ObservableArrayList()
	val onMovieClickListener = OnItemClickListener<ActorMovieItemViewModel> { item -> view!!.onMovieClick(item.cast.get()) }
	val itemBindingMovies = ItemBinding.of<ActorMovieItemViewModel>(BR.item, R.layout.fragment_actor_detail_list_item)!!
			.bindExtra(BR.listener, onMovieClickListener)!!

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
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
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

			loadKnownFor(id!!)

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

	private fun loadKnownFor(id: Int) {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = ActorServiceProvider.ACTOR_MOVIES_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				state.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = ActorServiceProvider.service.actorMovies(id)
				val callback = ActorMoviesCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	inner class ActorMoviesCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<CreditsEntity>(callManager) {
		override fun onSuccess(call: Call<CreditsEntity>, response: Response<CreditsEntity>) {
			val data = response.body()!!.cast!!
			if (data.isNotEmpty()) {
				var moviesLimit = 5
				if (data.size <= moviesLimit)
					moviesLimit = data.size - 1

				updateKnownForMovies(data.sliceArray(0..moviesLimit))
			}

			moviesCount.set(data.size)
		}

		override fun onError(call: Call<CreditsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<CreditsEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}

	private fun setState(data: ObservableField<ActorEntity>) {
		if (data.get() != null) {
			state.set(StatefulLayout.CONTENT)
		} else {
			state.set(StatefulLayout.EMPTY)
		}
	}

	private fun updateKnownForMovies(data: Array<CastEntity>) {
		knownForMovies.clear()
		data.mapTo(knownForMovies) { ActorMovieItemViewModel(it) }
	}
}