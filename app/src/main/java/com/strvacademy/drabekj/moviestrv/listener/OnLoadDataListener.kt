package com.strvacademy.drabekj.moviestrv.listener



interface OnLoadDataListener<T> {
	fun errorLoadingData()
	fun onLoadData(data :T)
}