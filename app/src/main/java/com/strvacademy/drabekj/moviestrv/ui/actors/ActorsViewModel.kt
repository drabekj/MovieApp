package com.strvacademy.drabekj.moviestrv.ui.actors

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.listener.OnItemClickListener
import com.strvacademy.drabekj.moviestrv.model.entity.ActorEntity
import com.strvacademy.drabekj.moviestrv.model.entity.ActorsResultsEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.ActorServiceProvider
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.rest.call.Callback
import org.alfonz.utility.NetworkUtility
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Response


class ActorsViewModel: BaseViewModel<ActorsView>() {
	val state = ObservableField<Int>()

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
		if (NetworkUtility.isOnline(MoviesApplication.getContext())) {
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
}