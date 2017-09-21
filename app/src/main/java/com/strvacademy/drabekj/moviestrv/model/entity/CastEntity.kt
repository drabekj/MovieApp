package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CastEntity {

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("name")
	var name: String? = null

	@SerializedName("character")
	var character: String? = null

	@SerializedName("profile_path")
	var profilePath: Any? = null

	@SerializedName("title")
	var title: String? = null

	@SerializedName("poster_path")
	var posterPath: String? = null

	@SerializedName("cast_id")
	var castId: Int? = null

	@SerializedName("credit_id")
	var creditId: String? = null

	@SerializedName("gender")
	var gender: Int? = null

	// Position in the List when showing cast (starting with 0)
	@SerializedName("order")
	var order: Int? = null

	@SerializedName("release_date")
	var releaseDate: String? = null

	@SerializedName("vote_count")
	var voteCount: Int? = null

	@SerializedName("video")
	var video: Boolean? = null

	@SerializedName("adult")
	var adult: Boolean? = null

	@SerializedName("vote_average")
	var voteAverage: Double? = null

	@SerializedName("genre_ids")
	var genreIds: List<Int>? = null

	@SerializedName("original_language")
	var originalLanguage: String? = null

	@SerializedName("original_title")
	var originalTitle: String? = null

	@SerializedName("popularity")
	var popularity: Double? = null

	@SerializedName("backdrop_path")
	var backdropPath: String? = null

	@SerializedName("overview")
	var overview: String? = null

	constructor(id: Int?, title: String?, releaseDate: String?, posterPath: String?, voteAverage: Double?) {
		this.id = id
		this.title = title
		this.releaseDate = releaseDate
		this.posterPath = posterPath
		this.voteAverage = voteAverage
	}
}