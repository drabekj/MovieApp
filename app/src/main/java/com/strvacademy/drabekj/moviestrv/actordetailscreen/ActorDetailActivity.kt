package com.strvacademy.drabekj.moviestrv.actordetailscreen

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.actorsscreen.ActorsFragment
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity


class ActorDetailActivity: BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_actor_detail)

		setupFragment(savedInstanceState)
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}

	private fun setupFragment(savedInstanceState: Bundle?) {
		if (findViewById<View>(R.id.fragment_container) != null && savedInstanceState == null) {
			// get actorId from bundle
			val actorId = intent.getIntExtra(ActorsFragment.EXTRA_KEY_ACTOR_ID, -1)

			// setup fragment
			val actorDetailFragment: ActorDetailFragment = ActorDetailFragment.newInstance(actorId)
			supportFragmentManager.beginTransaction().add(R.id.fragment_container, actorDetailFragment).commit()
		}
	}
}