package com.strvacademy.drabekj.moviestrv.ui.movies

import android.database.MatrixCursor
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface MoviesView : BaseView {
	fun showMovieResults(cursor: MatrixCursor)
}