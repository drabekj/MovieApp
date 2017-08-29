package com.strvacademy.drabekj.moviestrv.listener

import com.strvacademy.drabekj.moviestrv.model.Movie


interface OnLoadDataListener<T> {
	fun errorLoadingData()
	fun onLoadData(data :T)
}