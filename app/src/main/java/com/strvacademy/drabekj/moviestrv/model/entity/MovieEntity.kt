package com.strvacademy.drabekj.moviestrv.model.entity

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.google.gson.annotations.SerializedName
import org.alfonz.utility.Logcat
import java.text.SimpleDateFormat
import java.util.*

class MovieEntity {
	// TODO how to modify data when creating (e.g. url -> append base, date -> change format)

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("title")
	var title: String? = null

	@SerializedName("vote_average")
	var voteAverage: Double? = null

	@SerializedName("poster_path")
	var posterPath: String? = null

	@SerializedName("release_date")
	var releaseDate: String? = null

	@SerializedName("adult")
	var adult: Boolean? = null

	@SerializedName("backdrop_path")
	var backdropPath: String? = null

	@SerializedName("belongs_to_collection")
	var belongsToCollection: Any? = null

	@SerializedName("budget")
	var budget: Int? = null

	@SerializedName("genres")
	var genres: List<GenreEntity>? = null

	@SerializedName("homepage")
	var homepage: String? = null

	@SerializedName("imdb_id")
	var imdbId: String? = null

	@SerializedName("original_language")
	var originalLanguage: String? = null

	@SerializedName("original_title")
	var originalTitle: String? = null

	@SerializedName("overview")
	var overview: String? = null

	@SerializedName("popularity")
	var popularity: Double? = null

	@SerializedName("revenue")
	var revenue: Int? = null

	@SerializedName("runtime")
	var runtime: Int? = null

	@SerializedName("status")
	var status: String? = null

	@SerializedName("tagline")
	var tagline: String? = null

	@SerializedName("video")
	var video: Boolean? = null

	@SerializedName("vote_count")
	var voteCount: Int? = null

	val director = ObservableField<CrewEntity>()

	fun getReleaseYear(): String {
		val df1 = SimpleDateFormat("yyyy-MM-dd", Locale.US)
		val df2 = SimpleDateFormat("yyyy", Locale.US)
		val date = df1.parse(releaseDate)
		val year = df2.format(date)

		return year.toString()
	}

//	constructor(it: CastEntity) {
//		id = it.id
//		title = it.title
//		voteAverage = it.voteAverage
//		posterPath = it.posterPath
//		releaseDate = it.releaseDate
//	}
}
