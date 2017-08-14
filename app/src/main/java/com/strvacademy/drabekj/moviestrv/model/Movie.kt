package com.strvacademy.drabekj.moviestrv.model

data class Movie(var id: Int, var name: String, var director: String, var releaseDate: String, var description: String,
			var rating: String, var gallery: Array<String>, var cast: Array<Actor>) {
}
