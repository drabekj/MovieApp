package com.strvacademy.drabekj.moviestrv.mainscreen

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.strvacademy.drabekj.moviestrv.actorsscreen.ActorsActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar(INDICATOR_NONE, getString(R.string.mainToolbarTitle))
        setupViewPager()
		setupBottomNavView()
    }

	// TODO toolbar (Is this how it's supposed to be done with Alfonz?)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search -> {
                showToast("Search Action")
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun setupViewPager() {
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.tab_popular_movies)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.tab_now_playing_movies)))
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        // Instantiate a ViewPager and a PagerAdapter.
        val mPagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        pager.adapter = mPagerAdapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onBackPressed() {
        if (pager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            pager.currentItem = pager.currentItem - 1
        }
    }

	private fun setupBottomNavView() {
		bottom_navigation.setOnNavigationItemSelectedListener(
				{ item ->
					when (item.itemId) {
						R.id.action_movies -> {
							startActivity(Intent(this, MainActivity::class.java))
						}
						R.id.action_actors -> {
							startActivity(Intent(this, ActorsActivity::class.java))
						}
						R.id.action_profile -> {
							startActivity(Intent(this, MainActivity::class.java))
						}
					}
					true
				})
	}
}
