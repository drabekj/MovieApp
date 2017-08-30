package com.strvacademy.drabekj.moviestrv.ui.moviedetail

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.view.StatefulLayout
import me.tatarka.bindingcollectionadapter2.ItemBinding
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.listener.OnItemClickListener
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.MovieServiceProvider
import me.tatarka.bindingcollectionadapter2.BR
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.NetworkUtility
import retrofit2.Call
import retrofit2.Response


class MovieDetailViewModel : BaseViewModel<MovieDetailView>() {
	var id: Int? = null
	val state = ObservableField<Int>()
	val movie = ObservableField<MovieEntity>()

	val gallery: ObservableList<String> = ObservableArrayList()
	val itemBindingGallery = ItemBinding.of<String>(BR.item, R.layout.fragment_movie_detail_gallery_list_item)!!

	val cast: ObservableList<MovieItemViewModel> = ObservableArrayList()
	val onCastClickListener = OnItemClickListener<MovieItemViewModel> {
		item ->
		Toast.makeText(MoviesApplication.getContext(), "click " + item.actor.get().name, Toast.LENGTH_SHORT).show()
	}
	val itemBindingCast = ItemBinding.of<MovieItemViewModel>(BR.itemViewModel, R.layout.fragment_movie_detail_cast_list_item)
			.bindExtra(BR.listener, onCastClickListener)!!

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())


	override fun onStart() {
		super.onStart()
		if (movie.get() == null)
			loadData(id!!)
	}

	fun loadData(id: Int) {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		loadMovie(id)
	}

	private fun loadMovie(id: Int) {
		if (NetworkUtility.isOnline(MoviesApplication.getContext())) {
			val callType = MovieServiceProvider.MOVIE_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				state.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = MovieServiceProvider.service.movie(id)
				val callback = MovieCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	inner class MovieCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<MovieEntity>(callManager) {
		override fun onSuccess(call: Call<MovieEntity>, response: Response<MovieEntity>) {
			movie.set(response.body())
			setState(movie)

			//		updateGallery(data)
			//		updateCast(data)
		}

		override fun onError(call: Call<MovieEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
			setState(movie)
		}

		override fun onFail(call: Call<MovieEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
			setState(movie)
		}
	}

	private fun setState(data: ObservableField<MovieEntity>) {
		if (data.get() != null) {
			state.set(StatefulLayout.CONTENT)
		} else {
			state.set(StatefulLayout.EMPTY)
		}
	}

	private fun updateGallery(data: MovieEntity) {
		gallery.clear()
//		gallery.addAll(data.gallery)
	}

	private fun updateCast(data: MovieEntity) {
		cast.clear()
//		data.cast.mapTo(cast) { MovieItemViewModel(it) }
	}
}