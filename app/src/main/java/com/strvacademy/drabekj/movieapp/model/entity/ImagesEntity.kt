package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName


class ImagesEntity {

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("backdrops")
	var backdrops: List<BackdropEntity>? = null

	@SerializedName("posters")
	var posters: List<PosterEntity>? = null

}