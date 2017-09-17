package com.strvacademy.drabekj.moviestrv.ui.moviedetail

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseViewModel
import org.alfonz.view.StatefulLayout
import me.tatarka.bindingcollectionadapter2.ItemBinding
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.listener.OnItemClickListener
import com.strvacademy.drabekj.moviestrv.model.entity.*
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.MovieServiceProvider
import me.tatarka.bindingcollectionadapter2.BR
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.Logcat
import org.alfonz.utility.NetworkUtility
import retrofit2.Call
import retrofit2.Response


class MovieDetailViewModel : BaseViewModel<MovieDetailView>() {
	var id: Int? = null
	val state = ObservableField<Int>()
	val movie = ObservableField<MovieEntity>()

	val gallery: ObservableList<BackdropEntity> = ObservableArrayList()
	val itemBindingGallery = ItemBinding.of<BackdropEntity>(BR.item, R.layout.fragment_movie_detail_gallery_list_item)!!

	val cast: ObservableList<MovieCastItemViewModel> = ObservableArrayList()
	val onCastClickListener = OnItemClickListener<MovieCastItemViewModel> {
		item -> view?.startActorDetailActivity(item.actor.get().id!!)
	}
	val itemBindingCast = ItemBinding.of<MovieCastItemViewModel>(BR.itemViewModel, R.layout.fragment_movie_detail_cast_list_item)
			.bindExtra(BR.listener, onCastClickListener)!!

	val videos: ObservableList<VideoEntity> = ObservableArrayList()

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

			loadMovieImages(response.body()!!.id!!)
			loadMovieCredits(response.body()!!.id!!)
			loadMovieVideos(response.body()!!.id!!)

			setState(movie)
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

	//	load Images
	private fun loadMovieImages(id: Int) {
		if (NetworkUtility.isOnline(MoviesApplication.getContext())) {
			val callType = MovieServiceProvider.MOVIE_IMAGES_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = MovieServiceProvider.service.movieImages(id)
				val callback = MovieImagesCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	inner class MovieImagesCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<ImagesEntity>(callManager) {
		override fun onSuccess(call: Call<ImagesEntity>, response: Response<ImagesEntity>) {
			// save some cast from response
			val data = response.body()!!.backdrops!!
			if (data.isEmpty())
				return

			var galleryLimit = 5
			if (data.size <= galleryLimit)
				galleryLimit = data.size - 1

			updateGallery(data.slice(0..galleryLimit))
		}

		override fun onError(call: Call<ImagesEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<ImagesEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}

	//	load Credits
	private fun loadMovieCredits(id: Int) {
		if (NetworkUtility.isOnline(MoviesApplication.getContext())) {
			val callType = MovieServiceProvider.MOVIE_CREDITS_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = MovieServiceProvider.service.movieCredits(id)
				val callback = MovieCreditsCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	inner class MovieCreditsCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<CreditsEntity>(callManager) {
		override fun onSuccess(call: Call<CreditsEntity>, response: Response<CreditsEntity>) {
			// save some cast from response
			val dataCast = response.body()!!.cast!!
			if (dataCast.isNotEmpty()) {
				var castLimit = 5
				if (dataCast.size <= castLimit)
					castLimit = dataCast.size - 1

				updateCast(dataCast.sliceArray(0..castLimit))
			}

			// save director from response
			response.body()!!.crew!!
					.filter { it.job.equals("Director") }
					.forEach { movie.get().director.set(it) }
		}

		override fun onError(call: Call<CreditsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<CreditsEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}


	//	load Videos
	private fun loadMovieVideos(id: Int) {
		if (NetworkUtility.isOnline(MoviesApplication.getContext())) {
			val callType = MovieServiceProvider.MOVIE_VIDEOS_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = MovieServiceProvider.service.movieVideos(id)
				val callback = MovieVideosCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	inner class MovieVideosCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<VideosResultsEntity>(callManager) {
		override fun onSuccess(call: Call<VideosResultsEntity>, response: Response<VideosResultsEntity>) {
			Logcat.d("YouTube onSuccess callback")
			// save some cast from response
			val data = response.body()!!.results!!
			if (data.isEmpty())
				return

			var videosLimit = 1
			if (data.size <= videosLimit)
				videosLimit = data.size - 1

			updateVideos(data.sliceArray(0..videosLimit))

			(view as MovieDetailFragment).initializeYouTubePlayer(videos.first().key!!)
		}

		override fun onError(call: Call<VideosResultsEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<VideosResultsEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}

	private fun setState(data: ObservableField<MovieEntity>) {
		if (data.get() != null) {
			state.set(StatefulLayout.CONTENT)
		} else {
			state.set(StatefulLayout.EMPTY)
		}
	}

	private fun updateGallery(data: List<BackdropEntity>) {
		gallery.clear()
		gallery.addAll(data)
	}

	private fun updateCast(data: Array<CastEntity>) {
		cast.clear()
		data.mapTo(cast) { MovieCastItemViewModel(it) }
	}

	private fun updateVideos(data: Array<VideoEntity>) {
		videos.clear()
		videos.addAll(data)
	}
}