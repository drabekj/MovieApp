package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName


class AccountEntity {

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("username")
	var username: String? = null

	@SerializedName("name")
	var name: String? = null

	@SerializedName("avatar")
	var avatar: AvatarEntity? = null

	@SerializedName("include_adult")
	var includeAdult: Boolean? = null

	@SerializedName("iso_639_1")
	var iso6391: String? = null

	@SerializedName("iso_3166_1")
	var iso31661: String? = null

}