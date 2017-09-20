package com.strvacademy.drabekj.moviestrv.ui.moviedetail

import android.os.Bundle
import android.view.*
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.strvacademy.drabekj.moviestrv.MoviesConfig
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMovieDetailBinding
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseFragment
import org.alfonz.mvvm.AlfonzActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.strvacademy.drabekj.moviestrv.ui.actordetail.ActorDetailActivity
import org.alfonz.utility.Logcat


class MovieDetailFragment : BaseFragment<MovieDetailView, MovieDetailViewModel, FragmentMovieDetailBinding>(),
		MovieDetailView {

	var mYouTubePlayer: YouTubePlayer? = null


	override fun getViewModelClass(): Class<MovieDetailViewModel> {
		return MovieDetailViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMovieDetailBinding {
		return FragmentMovieDetailBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		setupToolbar()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (arguments != null)
			viewModel.id = arguments.getInt(ARG_MOVIE_ID)
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		if (viewModel.videos.isNotEmpty())
			initializeYouTubePlayer(viewModel.videos.first().key!!)
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater!!.inflate(R.menu.toolbar_movie_detail, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item!!.itemId) {
			R.id.action_favorite -> {
				viewModel.setFavourite()
				return true
			}

			R.id.action_share -> {
				showToast("Share action")
				return true
			}

			else -> return super.onOptionsItemSelected(item)
		}
	}

	override fun initializeYouTubePlayer(url_key: String) {
		val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()
		youTubePlayerFragment.initialize(MoviesConfig.YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {

			override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
				Logcat.d("onInitializationSuccess: " + wasRestored + " videoId: " + url_key)

				mYouTubePlayer = player
				mYouTubePlayer!!.setFullscreen(false)
				mYouTubePlayer!!.setShowFullscreenButton(false)

				if (!wasRestored)
					mYouTubePlayer!!.cueVideo(url_key)
			}

			override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
				Logcat.d("Error initializing YouTubePlayer")
			}
		})
		val transaction = childFragmentManager.beginTransaction()
		transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit()
	}

	override fun onFullCastClick() {
		showToast("Show Full Cast")
	}

	override fun startActorDetailActivity(actorId: Int) {
		ActorDetailActivity.startAsIntent(activity, actorId)
	}

	override fun showNeedToBeLoggedInToast() {
		showToast(getString(R.string.need_to_be_logged_in))
	}

	private fun setupToolbar() {
		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_BACK, "")
		setHasOptionsMenu(true)
	}


	companion object {
		private val ARG_MOVIE_ID = "param_movie_id"

		fun newInstance(param: Int): MovieDetailFragment {
			val fragment = MovieDetailFragment()
			val args = Bundle()
			args.putInt(ARG_MOVIE_ID, param)
			fragment.arguments = args
			return fragment
		}
	}
}