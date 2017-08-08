package com.strvacademy.drabekj.moviestrv.profilescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentProfileBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import org.alfonz.mvvm.AlfonzActivity

class ProfileFragment: BaseFragment<ProfileView, ProfileViewModel, FragmentProfileBinding>(), ProfileView {
	private var mAdapter: ProfileFragmentAdapter? = null

	override fun getViewModelClass(): Class<ProfileViewModel> {
		return ProfileViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentProfileBinding {
		return FragmentProfileBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		setupAdapter()
		setupToolbar()
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater!!.inflate(R.menu.profile_toolbar_menu, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item!!.itemId) {
			R.id.action_settings -> {
				showToast("Settings Action")
				return true
			}
			R.id.action_logout -> {
				showToast("Logout Action")
				return true
			}
			else -> return super.onOptionsItemSelected(item)
		}
	}

	private fun setupAdapter() {
		if (mAdapter == null) {
			mAdapter = ProfileFragmentAdapter(this, viewModel)
			binding.fragmentProfileListRecycler.adapter = mAdapter
		}
	}

	private fun setupToolbar() {
		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_NONE, "")
		setHasOptionsMenu(true)
	}

	override fun onAllFavouriteClick() {
		showToast("Show all favourite")
	}

	override fun onFavMovieClick(m: String) {
		showToast("Show movie detail " + m)
	}

	companion object {
		fun newInstance(): ProfileFragment {
			return ProfileFragment()
		}
	}
}