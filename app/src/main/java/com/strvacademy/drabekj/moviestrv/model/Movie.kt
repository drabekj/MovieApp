package com.strvacademy.drabekj.moviestrv.model

import com.google.gson.annotations.SerializedName

data class Movie(
		var id: Int,

		@SerializedName("title")
		var name: String,
		var director: String,

		@SerializedName("release_date")
		var releaseDate: String,

		@SerializedName("overview")
		var description: String,
		var rating: String,
		var gallery: Array<String>?,
		var cast: Array<Actor>?
)