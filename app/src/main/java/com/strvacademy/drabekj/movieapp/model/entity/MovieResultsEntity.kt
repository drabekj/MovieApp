package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName


class MovieResultsEntity {

	@SerializedName("results")
	var results: Array<MovieEntity>? = null

}