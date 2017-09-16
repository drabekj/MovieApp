package com.strvacademy.drabekj.moviestrv.ui.movies

import android.database.MatrixCursor
import android.databinding.ObservableArrayList
import android.support.v4.widget.CursorAdapter
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel


class MoviesViewModel : BaseViewModel<MoviesView>() {

	fun createSearchAdapter(): CursorAdapter {
		val cursor = createResultsCursor()
		return SearchResultAdapter(MoviesApplication.getContext(), cursor, 0)
	}

	fun createResultsCursor(query : String? = null): MatrixCursor {
		var data = listOf(
				Movie(0,"Minions", "Director", "2017-01-01", "This is the description", "9.1", null, null),
				Movie(0,"Iron Man", "Director", "2017-01-01", "This is the description", "9.1", null, null),
				Movie(0,"Minions 2", "Director", "2017-01-01", "This is the description", "9.1", null, null),
				Movie(0,"Iron Man 2", "Director", "2017-01-01", "This is the description", "9.1", null, null),
				Movie(0,"The amazing Spider Man", "Director", "2017-01-01", "This is the description", "9.1", null, null),
				Movie(0,"Spider Man Homecoming", "Director", "2017-01-01", "This is the description", "9.1", null, null)
		)
		if (query != null)
			data = data.filter { item -> item.name.contains(query) }

		val cursor = MatrixCursor(arrayOf("_id", "text"))
		val temp = arrayOfNulls<String>(2)
		var id = 0
		for (item in data) {
			temp[0] = Integer.toString(id++)
			temp[1] = item.name
			cursor.addRow(temp)
		}

		return cursor
	}

}