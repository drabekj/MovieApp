package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName

class VideosResultsEntity {

	@SerializedName("results")
	var results: Array<VideoEntity>? = null

}