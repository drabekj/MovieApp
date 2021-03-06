package com.strvacademy.drabekj.movieapp.ui.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.ui.movies.moviesPage.MoviesPageFragment
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseActivity


class MovieDetailActivity: BaseActivity() {

    companion object {
        val EXTRA_KEY_MOVIE_ID = "EXTRA_KEY_MOVIE_ID"

        fun startAsIntent(context: Context, movieId: Int) {
            val extras = Bundle()

            extras.putInt(EXTRA_KEY_MOVIE_ID, movieId)

            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtras(extras)

            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupFragment(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (findViewById<View>(R.id.fragment_container) != null && savedInstanceState == null) {
            // get movieId from bundle
            val movieId = intent.getIntExtra(MoviesPageFragment.EXTRA_KEY_MOVIE_ID, -1)

            // Metrics
            movieDetailMetric(movieId)

            // setup fragment
            val movieDetailFragment: MovieDetailFragment = MovieDetailFragment.newInstance(movieId)
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, movieDetailFragment).commit()
        }
    }

    private fun movieDetailMetric(id: Int) {
        Answers.getInstance().logContentView(ContentViewEvent()
                .putContentType("screen")
                .putContentName("MovieDetail")
                .putContentId(id.toString()))
    }
}