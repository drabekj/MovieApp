package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName


class MovieResultsEntity {

	@SerializedName("results")
	var results: Array<MovieEntity>? = null

}