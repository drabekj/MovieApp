package com.strvacademy.drabekj.moviestrv.moviedetailscreen

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.mainscreen.MainFragment
import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.MoviesPageFragment
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity


class MovieDetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupActionBar(INDICATOR_BACK, "")
        val upArrow: Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        supportActionBar!!.setHomeAsUpIndicator(upArrow)

        setupFragment(savedInstanceState)
    }

    // TODO toolbar (Is this how it's supposed to be done with Alfonz?)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_toolbar_menu, menu)
        return true
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

//    TODO ? better?
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (findViewById<View>(R.id.fragment_container) != null && savedInstanceState == null) {
            // get movieId from bundle
            val movieId = intent.getIntExtra(MoviesPageFragment.EXTRA_KEY_MOVIE_ID, -1)

            // setup fragment
            val movieDetailFragment: MovieDetailFragment = MovieDetailFragment.newInstance(movieId)
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, movieDetailFragment).commit()
        }
    }
}