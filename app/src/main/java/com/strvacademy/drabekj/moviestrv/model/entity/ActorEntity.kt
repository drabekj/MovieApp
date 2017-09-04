package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ActorEntity {

	@SerializedName("popularity")
	var popularity: Double? = null

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("profile_path")
	var profilePath: String? = null

	@SerializedName("name")
	var name: String? = null

	@SerializedName("known_for")
	var knownFor: List<MovieEntity>? = null

	@SerializedName("adult")
	var adult: Boolean? = null

}