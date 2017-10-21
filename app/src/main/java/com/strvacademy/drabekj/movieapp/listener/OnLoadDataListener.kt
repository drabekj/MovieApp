package com.strvacademy.drabekj.movieapp.listener



interface OnLoadDataListener<T> {
	fun errorLoadingData()
	fun onLoadData(data :T)
}