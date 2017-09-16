package com.strvacademy.drabekj.moviestrv.ui.movies

import android.content.Context
import android.database.Cursor
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.widget.CursorAdapter
import android.view.View
import android.view.ViewGroup
import com.strvacademy.drabekj.moviestrv.R
import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.BR
import com.strvacademy.drabekj.moviestrv.model.entity.ActorEntity
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity


class SearchActorResultsAdapter(context: Context?, c: Cursor?, flags: Int) : CursorAdapter(context, c, flags) {
	companion object {
		val RESULT_COLUMN_ID = "_id"
		val RESULT_COLUMN_NAME = "name"
		val RESULT_COLUMN_PROFILE_PATH = "profilePath"
	}

	override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
		val searchMovieItemBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), R.layout.search_actor_item, parent, false)

		return searchMovieItemBinding!!.root
	}

	override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
		val binding = DataBindingUtil.getBinding<ViewDataBinding>(view)

		val actor = ActorEntity()
		actor.id = cursor?.getInt( cursor.getColumnIndex(RESULT_COLUMN_ID) )
		actor.name = cursor?.getString( cursor.getColumnIndex(RESULT_COLUMN_NAME) )
		actor.profilePath = cursor?.getString( cursor.getColumnIndex(RESULT_COLUMN_PROFILE_PATH) )

		binding.setVariable(BR.data, actor)
	}
}