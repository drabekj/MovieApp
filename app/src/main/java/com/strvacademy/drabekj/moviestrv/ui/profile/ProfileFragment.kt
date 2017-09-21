package com.strvacademy.drabekj.moviestrv.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentProfileBinding
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.ui.allFilms.AllFilmsActivity
import com.strvacademy.drabekj.moviestrv.ui.moviedetail.MovieDetailActivity
import com.strvacademy.drabekj.moviestrv.ui.startup.StartupActivity
import com.strvacademy.drabekj.moviestrv.utils.KeyStoreUtil
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseFragment
import org.alfonz.mvvm.AlfonzActivity
import org.alfonz.view.StatefulLayout

class ProfileFragment : BaseFragment<ProfileView, ProfileViewModel, FragmentProfileBinding>(), ProfileView {

	override fun getViewModelClass(): Class<ProfileViewModel> {
		return ProfileViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentProfileBinding {
		return FragmentProfileBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		setupToolbar()
	}

	override fun onResume() {
		super.onResume()
		setupLoggedOutState()
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater!!.inflate(R.menu.toolbar_profile, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item!!.itemId) {
			R.id.action_settings -> {
				showToast("Settings Action")
				return true
			}
			R.id.action_logout -> {
				KeyStoreUtil.clearSecret()
				setupLoggedOutState()
				showToast("Logged out")
				return true
			}
			else -> return super.onOptionsItemSelected(item)
		}
	}

	private fun setupToolbar() {
		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_NONE, "")
		setHasOptionsMenu(true)
	}

	override fun onAllFavouriteClick() {
		startFavoriteFilmsActivity()
	}

	fun startFavoriteFilmsActivity() {
		startActivity(Intent(context, AllFilmsActivity::class.java))
	}

	override fun onFavMovieClick(movie: MovieEntity) {
		showToast("Show movie detail " + movie.title)
	}

	private fun setupLoggedOutState() {
		if (!MoviesApplication.isUserLoggedIn())
			viewModel.state.set(StatefulLayout.EMPTY)

		activity.findViewById<Button>(R.id.login_btn).setOnClickListener({
			view -> StartupActivity.startAsIntent(activity)
		})
	}

	companion object {
		val TAG = "profile_fragment"

		fun newInstance(): ProfileFragment {
			return ProfileFragment()
		}
	}
}