package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName


class FavouriteEntity {

	@SerializedName("media_type")
	var mediaType: String? = null

	@SerializedName("media_id")
	var mediaId: Int? = null

	@SerializedName("favorite")
	var favorite: Boolean? = null

	constructor(mediaType: String?, mediaId: Int?, favorite: Boolean?) {
		this.mediaType = mediaType
		this.mediaId = mediaId
		this.favorite = favorite
	}
}