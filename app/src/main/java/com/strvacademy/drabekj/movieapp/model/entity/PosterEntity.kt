package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName


class PosterEntity {

	@SerializedName("file_path")
	var filePath: String? = null

	@SerializedName("width")
	var width: Int? = null

	@SerializedName("height")
	var height: Int? = null

	@SerializedName("aspect_ratio")
	var aspectRatio: Double? = null

	@SerializedName("iso_639_1")
	var iso6391: String? = null

	@SerializedName("vote_average")
	var voteAverage: Double? = null

	@SerializedName("vote_count")
	var voteCount: Int? = null

}