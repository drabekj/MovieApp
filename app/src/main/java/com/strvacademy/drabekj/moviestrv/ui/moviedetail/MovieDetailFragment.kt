package com.strvacademy.drabekj.moviestrv.ui.moviedetail

import android.app.FragmentTransaction
import android.os.Bundle
import android.view.*
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.strvacademy.drabekj.moviestrv.MoviesConfig
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMovieDetailBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.alfonz.mvvm.AlfonzActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import org.alfonz.utility.Logcat
import java.security.Provider


class MovieDetailFragment: BaseFragment<MovieDetailView, MovieDetailViewModel, FragmentMovieDetailBinding>(),
        MovieDetailView {

    override fun getViewModelClass(): Class<MovieDetailViewModel> {
        return MovieDetailViewModel::class.java
    }

    override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(inflater!!)
    }

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()
		youTubePlayerFragment.initialize(MoviesConfig.YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {

			override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
				Logcat.d("Success initializing YouTubePlayer")
				if (!wasRestored) {
					player.cueVideo("2zNSgSzhBfM")
				}

			}

			override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
				Logcat.d("Error initializing YouTubePlayer")
			}
		})
		val transaction = childFragmentManager.beginTransaction()
		transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit()

		return super.onCreateView(inflater, container, savedInstanceState)
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