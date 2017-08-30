package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName

class MovieEntity {

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("title")
	var title: String? = null

	@SerializedName("vote_average")
	var voteAverage: Double? = null

	@SerializedName("poster_path")
	var posterPath: String? = null

	@SerializedName("release_date")
	var releaseDate: String? = null

	@SerializedName("adult")
	var adult: Boolean? = null

	@SerializedName("backdrop_path")
	var backdropPath: String? = null

	@SerializedName("belongs_to_collection")
	var belongsToCollection: Any? = null

	@SerializedName("budget")
	var budget: Int? = null

	@SerializedName("genres")
	var genres: List<GenreEntity>? = null

	@SerializedName("homepage")
	var homepage: String? = null

	@SerializedName("imdb_id")
	var imdbId: String? = null

	@SerializedName("original_language")
	var originalLanguage: String? = null

	@SerializedName("original_title")
	var originalTitle: String? = null

	@SerializedName("overview")
	var overview: String? = null

	@SerializedName("popularity")
	var popularity: Double? = null

	@SerializedName("revenue")
	var revenue: Int? = null

	@SerializedName("runtime")
	var runtime: Int? = null

	@SerializedName("status")
	var status: String? = null

	@SerializedName("tagline")
	var tagline: String? = null

	@SerializedName("video")
	var video: Boolean? = null

	@SerializedName("vote_count")
	var voteCount: Int? = null
}
