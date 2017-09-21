package com.strvacademy.drabekj.moviestrv.ui.profile

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.listener.OnItemClickListener
import com.strvacademy.drabekj.moviestrv.model.entity.AccountEntity
import com.strvacademy.drabekj.moviestrv.model.entity.GetFavouriteResponseEntity
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestHttpLogger
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RestResponseHandler
import com.strvacademy.drabekj.moviestrv.model.remote.rest.provider.AccountServiceProvider
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import org.alfonz.rest.HttpException
import org.alfonz.rest.call.CallManager
import org.alfonz.utility.NetworkUtility
import org.alfonz.view.StatefulLayout
import retrofit2.Call
import retrofit2.Response
import me.tatarka.bindingcollectionadapter2.ItemBinding


class ProfileViewModel: BaseViewModel<ProfileView>() {
	val state = ObservableField<Int>()
	val stateContent = ObservableField<Int>()
	val account = ObservableField<AccountEntity>()
	val favMoviesCount: ObservableField<Int> = ObservableField(0)

	val favMovies: ObservableArrayList<MovieEntity> = ObservableArrayList()
	val onMovieClickListener = OnItemClickListener<MovieEntity> { movie -> view?.onFavMovieClick(movie) }
	val itemBindingFav = ItemBinding.of<MovieEntity>(BR.movie, R.layout.fragment_profile_list_item)
			.bindExtra(BR.listener, onMovieClickListener)!!

	val mCallManager = CallManager(RestResponseHandler(), RestHttpLogger())


	override fun onStart() {
		super.onStart()

		if (MoviesApplication.isUserLoggedIn())
			loadData()
	}

	private fun loadData() {
		// load data from data provider...
		loadAccountDetail()
		loadFavouriteMovies()
	}

	private fun loadAccountDetail() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = AccountServiceProvider.ACCOUNT_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				state.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = AccountServiceProvider.service.account(MoviesApplication.sessionID!!)
				val callback = AccountDetailCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			state.set(StatefulLayout.OFFLINE)
		}
	}

	private fun loadFavouriteMovies() {
		if (NetworkUtility.isOnline(MoviesApplication.context)) {
			val callType = AccountServiceProvider.FAVOURITES_CALL_TYPE
			if (!mCallManager.hasRunningCall(callType)) {
				// show progress
				stateContent.set(StatefulLayout.PROGRESS)

				// enqueue call
				val call = AccountServiceProvider.service.favourites(MoviesApplication.accountID.get(), MoviesApplication.sessionID!!)
				val callback = FavouriteMoviesCallback(mCallManager)
				mCallManager.enqueueCall(call, callback, callType)
			}
		} else {
			// show offline
			stateContent.set(StatefulLayout.OFFLINE)
		}
	}

	inner class AccountDetailCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<AccountEntity>(callManager) {
		override fun onSuccess(call: Call<AccountEntity>, response: Response<AccountEntity>) {
			account.set(response.body())
			state.set(StatefulLayout.CONTENT)
		}

		override fun onError(call: Call<AccountEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
			state.set(StatefulLayout.OFFLINE)
		}

		override fun onFail(call: Call<AccountEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
			state.set(StatefulLayout.OFFLINE)
		}
	}

	inner class FavouriteMoviesCallback(callManager: CallManager) : org.alfonz.rest.call.Callback<GetFavouriteResponseEntity>(callManager) {
		override fun onSuccess(call: Call<GetFavouriteResponseEntity>, response: Response<GetFavouriteResponseEntity>) {
			updateFavMovies(response.body()?.results!!)

			if (favMoviesCount.get() > 0)
				stateContent.set(StatefulLayout.CONTENT)
			else
				stateContent.set(StatefulLayout.EMPTY)
		}

		override fun onError(call: Call<GetFavouriteResponseEntity>, exception: HttpException) {
			handleError(mCallManager.getHttpErrorMessage(exception))
			stateContent.set(StatefulLayout.OFFLINE)
		}

		override fun onFail(call: Call<GetFavouriteResponseEntity>, throwable: Throwable) {
			handleError(mCallManager.getHttpErrorMessage(throwable))
			stateContent.set(StatefulLayout.OFFLINE)
		}
	}

	private fun updateFavMovies(data: List<MovieEntity>) {
		val moviesDisplayLimit = 5
		favMoviesCount.set(data.size)

		if (data.isNotEmpty()) {
			favMovies.clear()
			if (data.size > moviesDisplayLimit)
				favMovies.addAll(data.slice(0..moviesDisplayLimit))
			else
				favMovies.addAll(data)
		}
	}
}