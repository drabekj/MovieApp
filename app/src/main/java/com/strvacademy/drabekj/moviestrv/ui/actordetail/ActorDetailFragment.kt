package com.strvacademy.drabekj.moviestrv.ui.actordetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorDetailBinding
import com.strvacademy.drabekj.moviestrv.model.entity.CastEntity
import com.strvacademy.drabekj.moviestrv.ui.allFilms.AllFilmsActivity
import com.strvacademy.drabekj.moviestrv.ui.moviedetail.MovieDetailActivity
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseFragment
import org.alfonz.mvvm.AlfonzActivity

class ActorDetailFragment: BaseFragment<ActorDetailView, ActorDetailViewModel, FragmentActorDetailBinding>(), ActorDetailView {

	override fun getViewModelClass(): Class<ActorDetailViewModel> {
		return ActorDetailViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentActorDetailBinding {
		return FragmentActorDetailBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		setupToolbar()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (arguments != null)
			viewModel.id = arguments.getInt(ARG_ACTOR_ID)
	}

	override fun onAllCreditsClick(actorId: Int) {
		startFavoriteFilmsActivity(actorId)
	}

	override fun onMovieClick(item: CastEntity) {
		if (item.id != null)
			startMovieDetailActivity(item.id!!)
	}

	private fun startMovieDetailActivity(id: Int) {
		val intent = Intent(activity, MovieDetailActivity::class.java)
		intent.putExtra(EXTRA_KEY_MOVIE_ID, id)
		startActivity(intent)
	}

	fun startFavoriteFilmsActivity(id: Int) {
		val intent = Intent(activity, AllFilmsActivity::class.java)
		intent.putExtra(EXTRA_KEY_ACTOR_ID, id)
		startActivity(intent)
	}

	private fun setupToolbar() {
		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_BACK, "")
		setHasOptionsMenu(true)
	}

	companion object {
		val EXTRA_KEY_MOVIE_ID = "EXTRA_KEY_MOVIE_ID"
		val EXTRA_KEY_ACTOR_ID = "EXTRA_KEY_ACTOR_ID"

		private val ARG_ACTOR_ID = "param_actor_id"

		fun newInstance(param: Int): ActorDetailFragment {
			val fragment = ActorDetailFragment()
			val args = Bundle()
			args.putInt(ARG_ACTOR_ID, param)
			fragment.arguments = args
			return fragment
		}
	}
}