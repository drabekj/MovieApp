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
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity


class SearchResultAdapter(context: Context?, c: Cursor?, flags: Int) : CursorAdapter(context, c, flags) {
	companion object {
		val RESULT_COLUMN_ID = "_id"
		val RESULT_COLUMN_TITLE = "title"
		val RESULT_COLUMN_POSTER_PATH = "posterPath"
		val RESULT_COLUMN_RELEASE_DATE = "releaseDate"
	}

	override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
		val searchMovieItemBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), R.layout.search_movie_item, parent, false)

		return searchMovieItemBinding!!.root
	}

	override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
		val binding = DataBindingUtil.getBinding<ViewDataBinding>(view)

		val movie = MovieEntity()
		movie.id = cursor?.getInt( cursor.getColumnIndex(RESULT_COLUMN_ID) )
		movie.title = cursor?.getString( cursor.getColumnIndex(RESULT_COLUMN_TITLE) )
		movie.posterPath = cursor?.getString( cursor.getColumnIndex(RESULT_COLUMN_POSTER_PATH) )
		movie.releaseDate = cursor?.getString( cursor.getColumnIndex(RESULT_COLUMN_RELEASE_DATE) )
		binding.setVariable(BR.data, movie)
	}
}