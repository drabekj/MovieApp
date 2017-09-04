package com.strvacademy.drabekj.moviestrv.ui.moviedetail

import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.strvacademy.drabekj.moviestrv.MoviesConfig
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMovieDetailBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.alfonz.mvvm.AlfonzActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageFragment
import org.alfonz.utility.Logcat
import java.security.Provider


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
				showToast("Mark as favorite")
				return true
			}

			R.id.action_share -> {
				showToast("Share action")
				return true
			}

			else -> return super.onOptionsItemSelected(item)
		}
	}

	fun initializeYouTubePlayer(url_key: String) {
		val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()
		youTubePlayerFragment.initialize(MoviesConfig.YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {

			override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
				Logcat.d("onInitializationSuccess: " + wasRestored + " videoId: " + url_key)

				mYouTubePlayer = player
				mYouTubePlayer!!.setFullscreen(false)
				mYouTubePlayer!!.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT)
				mYouTubePlayer!!.setOnFullscreenListener { arg0 ->
					val intent = Intent(activity, YouTubeFullscreenActivity::class.java)
					intent.putExtra(MoviesPageFragment.EXTRA_KEY_MOVIE_ID, id)
//					startActivity(intent)
					startActivityForResult(intent, 1)
				}

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

	private fun doLayout(provider: YouTubePlayer.Provider) {
//		val playerParams = provider.getLayoutParams() as LinearLayout.LayoutParams

		// When in fullscreen, the visibility of all other views than the player should be set to
		// GONE and the player should be laid out across the whole screen.
//		playerParams.width = LinearLayout.LayoutParams.MATCH_PARENT
//		playerParams.height = LinearLayout.LayoutParams.MATCH_PARENT
	}



	override fun onFullCastClick() {
		showToast("Show Full Cast")
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