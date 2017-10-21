package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName

class VideosResultsEntity {

	@SerializedName("results")
	var results: Array<VideoEntity>? = null

}