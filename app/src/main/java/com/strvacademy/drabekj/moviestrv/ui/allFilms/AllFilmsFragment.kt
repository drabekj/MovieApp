package com.strvacademy.drabekj.moviestrv.ui.allFilms

import android.os.Bundle
import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentAllFilmsBinding
import com.strvacademy.drabekj.moviestrv.ui.moviedetail.MovieDetailActivity
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseFragment

class AllFilmsFragment : BaseFragment<AllFilmsView, AllFilmsViewModel, FragmentAllFilmsBinding>(),
		AllFilmsView {

	override fun getViewModelClass(): Class<AllFilmsViewModel> {
		return AllFilmsViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentAllFilmsBinding {
		return FragmentAllFilmsBinding.inflate(inflater!!)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		if (arguments != null)
			viewModel.actorId = arguments.getInt(ARG_ACTOR_ID)
	}

	override fun onMovieClick(movieId: Int) {
		MovieDetailActivity.startAsIntent(context, movieId)
	}

	companion object {
		private val ARG_ACTOR_ID = "param_actor_id"

		fun newInstance(param: Int): AllFilmsFragment {
			val fragment = AllFilmsFragment()
			val args = Bundle()
			args.putInt(ARG_ACTOR_ID, param)
			fragment.arguments = args
			return fragment
		}
	}
}