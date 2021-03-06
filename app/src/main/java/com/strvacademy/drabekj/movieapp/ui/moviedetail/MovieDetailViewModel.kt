package com.strvacademy.drabekj.movieapp.ui.moviedetail

import android.databinding.ObservableField
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseViewModel
import org.alfonz.view.StatefulLayout
import me.tatarka.bindingcollectionadapter2.ItemBinding
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.strvacademy.drabekj.movieapp.MoviesApplication
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.listener.OnItemClickListener
import com.strvacademy.drabekj.movieapp.model.entity.*
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.movieapp.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.movieapp.model.remote.rest.provider.AccountServiceProvider
import com.strvacademy.drabekj.movieapp.model.remote.rest.provider.MovieServiceProvider
import me.tatarka.bindingcollectionadapter2.BR
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.Logcat
import org.alfonz.utility.NetworkUtility
import retrofit2.Call
import retrofit2.Response


class MovieDetailViewModel : BaseViewModel<MovieDetailView>() {
	var id: Int? = null
	var userLoggedIn = ObservableField<Boolean>(false)
	var isFavourite = ObservableField<Boolean>()
	val state = ObservableField<Int>()
	val movie = ObservableField<MovieEntity>()

	val gallery: ObservableArrayList<BackdropEntity> = ObservableArrayList()
	val itemBindingGallery = ItemBinding.of<BackdropEntity>(BR.item, R.layout.fragment_movie_detail_gallery_list_item)!!

	val cast: ObservableList<MovieCastItemViewModel> = ObservableArrayList()
	val onCastClickListener = OnItemClickListener<MovieCastItemViewModel> {
		item ->
		view?.startActorDetailActivity(item.actor.get().id!!)
	}
	val itemBindingCast = ItemBinding.of<MovieCastItemViewModel>(BR.itemViewModel, R.layout.fragment_movie_detail_cast_list_item)
			.bindExtra(BR.listener, onCastClickListener)!!

	val videos: ObservableList<VideoEntity> = ObservableArrayList()

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())


	override fun onStart() {
		super.onStart()

		if (MoviesApplication.isUserLoggedIn()) {
			userLoggedIn.set(true)
			loadIsMoveFavourite()
		}

		if (movie.get() == null)
			loadData()

		Logcat.d("All set!")
	}

	fun loadData() {
		loadMovieDetail()
	}

	fun setFavourite() {
		if (MoviesApplication.isUserLoggedIn()) {
			if (isFavourite.get()) {
				markAsFavourite(false)
				view?.showToast("Remove from favourites")
			}
			else {
				markAsFavourite(true)
				view?.showToast("Mark as favourite")
			}
		}
		else
			view?.showNeedToBeLoggedInToast()
	}

	private fun loadMovieDetail() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = MovieServiceProvider.MOVIE_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				state.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = MovieServiceProvider.service.movieDetail(id!!)
				val callback = MovieCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	private fun loadIsMoveFavourite() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = AccountServiceProvider.FAVOURITES_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				// TODO disable hearth button

				// enqueue call
				val call = AccountServiceProvider.service.favourites(MoviesApplication.accountID.get(), MoviesApplication.sessionID!!)
				val callback = FavouriteMoviesCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	private fun markAsFavourite(makeFavourite: Boolean) {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = AccountServiceProvider.MARK_FAVOURITE_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// enqueue call
				val call = AccountServiceProvider.service.markAsFavourite(
						MoviesApplication.accountID.get(),
						MoviesApplication.sessionID!!,
						FavouriteEntity("movie", id, makeFavourite))
				val callback = MarkAsFavouriteCallback(mCallManager)
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
			updateDirector()
			updateVideos()
			updateGallery()
			updateCast()

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

	inner class FavouriteMoviesCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<GetFavouriteResponseEntity>(callManager) {
		override fun onSuccess(call: Call<GetFavouriteResponseEntity>, response: Response<GetFavouriteResponseEntity>) {
			findMovieInFavourites(response.body()?.results!!)
			// TODO enable hearth button
		}

		override fun onError(call: Call<GetFavouriteResponseEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<GetFavouriteResponseEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}

	inner class MarkAsFavouriteCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<SetFavouriteResponseEntity>(callManager) {
		override fun onSuccess(call: Call<SetFavouriteResponseEntity>, response: Response<SetFavouriteResponseEntity>) {
			if (response.body()?.statusCode == 1) {
				Logcat.d("Mark as favourite finished with " + response.body()?.statusMessage)
				isFavourite.set(true)
			}
			else if (response.body()?.statusCode == 13) {
				Logcat.d("Remove from favourites finished with success: " + response.body()?.statusMessage)
				isFavourite.set(false)
			}
			Logcat.d("Marked as favourite: statusCode=" + response.body()?.statusCode + " message=" + response.body()?.statusMessage)
		}

		override fun onError(call: Call<SetFavouriteResponseEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
		}

		override fun onFail(call: Call<SetFavouriteResponseEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
		}
	}

	private fun findMovieInFavourites(favourites: List<MovieEntity>) {
		if (favourites.isEmpty())
			isFavourite.set(false)

		for (item in favourites) {
			if (item.id!! == id) {
				isFavourite.set(true)
				return
			}
			else
				isFavourite.set(false)
		}
	}

	private fun updateDirector() {
		val crew = movie.get().credits?.crew
		crew?.filter { it.job.equals("Director") }?.forEach { movie.get().director.set(it) }
	}

	private fun updateGallery() {
		val galleryDisplayLimit = 5

		var data = movie.get().images?.backdrops
		if (data != null) {
			if (data.size > galleryDisplayLimit)
				data = data.slice(0..galleryDisplayLimit)

			gallery.clear()
			gallery.addAll(data)
		}
	}

	private fun updateCast() {
		val castDisplayLimit = 5

		var data = movie.get().credits?.cast
		if (data != null) {
			if (data.size > castDisplayLimit)
				data = data.sliceArray(0..castDisplayLimit)

			cast.clear()
			data.mapTo(cast) { MovieCastItemViewModel(it) }
		}
	}

	private fun updateVideos() {
		val videosLimit = 1

		var data = movie.get().videos?.results
		if (data != null && data.isNotEmpty()) {
			if (data.size > videosLimit)
				data.sliceArray(0..videosLimit)

			videos.clear()
			videos.addAll(data)
			view?.initializeYouTubePlayer(videos.first().key!!)
		}
	}

	private fun setState(data: ObservableField<MovieEntity>) {
		if (data.get() != null) {
			state.set(StatefulLayout.CONTENT)
		} else {
			state.set(StatefulLayout.EMPTY)
		}
	}
}