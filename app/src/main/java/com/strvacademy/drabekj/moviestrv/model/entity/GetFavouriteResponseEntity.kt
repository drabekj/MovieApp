package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GetFavouriteResponseEntity {

	@SerializedName("page")
	var page: Int? = null

	@SerializedName("results")
	var results: List<MovieEntity>? = null

	@SerializedName("total_pages")
	var totalPages: Int? = null

	@SerializedName("total_results")
	var totalResults: Int? = null

}