package com.strvacademy.drabekj.movieapp.ui.actordetail

import android.content.Context
import android.os.Bundle
import android.view.View
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseActivity
import android.content.Intent
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent


class ActorDetailActivity : BaseActivity() {

	companion object {
		val EXTRA_KEY_ACTOR_ID = "EXTRA_KEY_ACTOR_ID"

		fun startAsIntent(context: Context, actorId: Int) {
			val extras = Bundle()

			extras.putInt(EXTRA_KEY_ACTOR_ID, actorId)

			val intent = Intent(context, ActorDetailActivity::class.java)
			intent.putExtras(extras)

			context.startActivity(intent)
		}
	}


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
			val actorId = intent.getIntExtra(EXTRA_KEY_ACTOR_ID, -1)

			// Metrics
			actorDetailMetric(actorId)

			// setup fragment
			val actorDetailFragment: ActorDetailFragment = ActorDetailFragment.newInstance(actorId)
			supportFragmentManager.beginTransaction().add(R.id.fragment_container, actorDetailFragment).commit()
		}
	}

	private fun actorDetailMetric(id: Int) {
		Answers.getInstance().logContentView(ContentViewEvent()
				.putContentType("screen")
				.putContentName("ActorDetail")
				.putContentId(id.toString()))
	}
}