package com.strvacademy.drabekj.moviestrv.model

data class MovieDataResponse(
		val id: Int,
		val vote_average: Float,
		val title: String,
		val poster_path: String,
		val overview: String,
		val release_date: String
)