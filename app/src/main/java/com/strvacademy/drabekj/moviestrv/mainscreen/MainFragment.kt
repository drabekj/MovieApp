package com.strvacademy.drabekj.moviestrv.mainscreen

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMainBinding
import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.MoviesPageFragmentAdapter
import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.ScreenSlidePagerAdapter
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.alfonz.mvvm.AlfonzActivity


class MainFragment: BaseFragment<MainView, MainViewModel, FragmentMainBinding>(), MainView {
	private var mAdapter: MoviesPageFragmentAdapter? = null


	override fun getViewModelClass(): Class<MainViewModel> {
		return MainViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMainBinding {
		return FragmentMainBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_NONE, getString(R.string.mainToolbarTitle))
		setupViewPager()
	}

	// TODO toolbar (Is this how it's supposed to be done with Alfonz?)
	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater!!.inflate(R.menu.main_toolbar_menu, menu)
		super.onCreateOptionsMenu(menu, inflater)
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
		val mPagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
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


	//	TODO ! refactoring
	//	override fun onBackPressed() {
	//		if (pager.currentItem == 0) {
	//			// If the user is currently looking at the first step, allow the system to handle the
	//			// Back button. This calls finish() on this activity and pops the back stack.
	//			super.onBackPressed()
	//		} else {
	//			// Otherwise, select the previous step.
	//			pager.currentItem = pager.currentItem - 1
	//		}
	//	}


	companion object {
		fun newInstance(): MainFragment {
			return MainFragment()
		}
	}
}