package com.strvacademy.drabekj.movieapp.ui.movies

import android.database.MatrixCursor
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface MoviesView : BaseView {
	fun showMovieResults(cursor: MatrixCursor)
}