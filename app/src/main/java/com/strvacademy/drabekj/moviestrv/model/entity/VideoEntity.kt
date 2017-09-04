package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName


class VideoEntity {

	@SerializedName("id")
	var id: String? = null

	@SerializedName("iso_639_1")
	var iso6391: String? = null

	@SerializedName("iso_3166_1")
	var iso31661: String? = null

	@SerializedName("key")
	var key: String? = null

	@SerializedName("name")
	var name: String? = null

	@SerializedName("site")
	var site: String? = null

	@SerializedName("size")
	var size: Int? = null

	@SerializedName("type")
	var type: String? = null

}