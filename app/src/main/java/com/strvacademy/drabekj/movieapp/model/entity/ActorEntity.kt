package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName


class ActorEntity {

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("name")
	var name: String? = null

	@SerializedName("birthday")
	var birthday: String? = null

	@SerializedName("profile_path")
	var profilePath: String? = null

	@SerializedName("known_for")
	var knownFor: List<MovieEntity>? = null

	@SerializedName("popularity")
	var popularity: Double? = null

	@SerializedName("adult")
	var adult: Boolean? = null

	@SerializedName("deathday")
	var deathday: Any? = null

	@SerializedName("also_known_as")
	var alsoKnownAs: List<String>? = null

	@SerializedName("gender")
	var gender: Int? = null

	@SerializedName("biography")
	var biography: String? = null

	@SerializedName("place_of_birth")
	var placeOfBirth: String? = null

	@SerializedName("imdb_id")
	var imdbId: String? = null

	@SerializedName("homepage")
	var homepage: Any? = null

}