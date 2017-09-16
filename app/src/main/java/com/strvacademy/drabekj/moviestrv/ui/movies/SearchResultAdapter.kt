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

	override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
		val searchMovieItemBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), R.layout.search_movie_item, parent, false)

		return searchMovieItemBinding!!.root
	}

	override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
		val binding = DataBindingUtil.getBinding<ViewDataBinding>(view)

		val movie = MovieEntity()
		movie.title = cursor?.getString( 1 )
		movie.id = cursor?.getString( 0 )!!.toInt()
		binding.setVariable(BR.data, movie)
	}
}