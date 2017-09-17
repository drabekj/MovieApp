package com.strvacademy.drabekj.moviestrv.ui.allFilms

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.listener.OnItemClickListener
import com.strvacademy.drabekj.moviestrv.model.entity.CastEntity
import com.strvacademy.drabekj.moviestrv.model.entity.CreditsEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.ActorServiceProvider
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.Logcat
import org.alfonz.utility.NetworkUtility
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Response

class AllFilmsViewModel : BaseViewModel<AllFilmsView>() {
	val state = ObservableField<Int>()
	var actorId: Int? = null
	val movies: ObservableArrayList<FilmItemViewModel> = ObservableArrayList()
	val onItemClickListener = OnItemClickListener<FilmItemViewModel> {
		item -> Toast.makeText(MoviesApplication.context, "click " + item.item.get().title, Toast.LENGTH_SHORT).show()
	}
	val itemBindingCast = ItemBinding.of<FilmItemViewModel>(BR.itemViewModel, R.layout.fragment_all_films_movie_list_item)
			.bindExtra(BR.listener, onItemClickListener)!!

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())


	override fun onStart() {
		Logcat.d("allMovies fragment -> actorId = " + actorId)

		super.onStart()
		if (movies.isEmpty()) {
			if (actorId == null || actorId == -1)
//				TODO urgent
			else
				loadActorsMovies(actorId!!)

		}
	}

	private fun loadActorsMovies(id: Int) {
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
			if (data.isNotEmpty())
				updateMovies(data)

			setState(movies)
		}

		override fun onError(call: Call<CreditsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
			setState(movies)
		}

		override fun onFail(call: Call<CreditsEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
			setState(movies)
		}
	}

	private fun setState(data: ObservableArrayList<FilmItemViewModel>) {
		if (data.isNotEmpty()) {
			state.set(StatefulLayout.CONTENT)
		} else {
			state.set(StatefulLayout.EMPTY)
		}
	}

	private fun updateMovies(data: Array<CastEntity>) {
		movies.clear()
		data.mapTo(movies) { FilmItemViewModel(it) }
	}
}